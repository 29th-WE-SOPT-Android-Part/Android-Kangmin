package org.sopt.soptandroidseminar.view.signin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.ActivitySignInBinding
import org.sopt.soptandroidseminar.api.data.SOPTSharedPreferences
import org.sopt.soptandroidseminar.view.activity.MainActivity
import org.sopt.soptandroidseminar.view.showToast
import org.sopt.soptandroidseminar.view.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: ActivitySignInBinding

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
                viewModel.login()
            }
        }

        binding.btnAutoLogin.setOnClickListener {
            binding.btnAutoLogin.isSelected = !binding.btnAutoLogin.isSelected
            SOPTSharedPreferences.setAutoLogin(binding.btnAutoLogin.isSelected)
        }
    }

    private fun initAutoLoginEvent() {
        if (SOPTSharedPreferences.getAutoLogin()) {
            showToast("자동 로그인")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun observeSuccessLogin() {
        viewModel.successLogIn.observe(this) {
            goToHomeActivity()
        }
    }

    private fun goToHomeActivity() {
        showToast("${SOPTSharedPreferences.getName()}님 환영합니다!")
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