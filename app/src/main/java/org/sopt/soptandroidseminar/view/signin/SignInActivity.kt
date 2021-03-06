package org.sopt.soptandroidseminar.view.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.data.SoptDataStore
import org.sopt.soptandroidseminar.databinding.ActivitySignInBinding
import org.sopt.soptandroidseminar.util.EventObserver
import org.sopt.soptandroidseminar.view.MainActivity
import org.sopt.soptandroidseminar.view.showToast
import org.sopt.soptandroidseminar.view.signup.SignUpActivity
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    private val viewModel by viewModels<SignInViewModel>()
    private lateinit var binding: ActivitySignInBinding
    @Inject
    lateinit var sharedPreferences: SoptDataStore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
        initAutoLoginEvent()
        observeSuccessLogin()
    }

    private fun initView() {
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }

        binding.btnLogin.setOnClickListener {
            if (viewModel.isInputBlank) {
                showToast("로그인 실패")
            } else {
                viewModel.login(binding.editId.text.toString(), binding.editPw.text.toString())
            }
        }

        binding.btnAutoLogin.setOnClickListener {
            binding.btnAutoLogin.isSelected = !binding.btnAutoLogin.isSelected
            sharedPreferences.setAutoLogin(binding.btnAutoLogin.isSelected)
        }
    }

    private fun initAutoLoginEvent() {
        if (sharedPreferences.getAutoLogin()) {
            showToast("자동 로그인")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun observeSuccessLogin() {
        viewModel.logIn.observe(this, EventObserver{
            when(it) {
                SignInViewModel.LOGIN_SUCCESS -> goToHomeActivity()
                SignInViewModel.LOGIN_FAILURE -> showToast("아이디/비밀번호 를 확인해주세요.")
                SignInViewModel.SERVER_FAILURE -> showToast("서버통신에 실패했습니다.")
            }
        })
    }

    private fun goToHomeActivity() {
        showToast("${sharedPreferences.getName()}님 환영합니다!")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.let {
                binding.apply {
                    editId.setText(it.getStringExtra("userId"))
                    editPw.setText(it.getStringExtra("userPw"))
                }
            }
        }
    }


}