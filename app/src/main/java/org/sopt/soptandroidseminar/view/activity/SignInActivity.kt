package org.sopt.soptandroidseminar.view.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.api.data.request.RequestLogin
import org.sopt.soptandroidseminar.databinding.ActivitySignInBinding
import org.sopt.soptandroidseminar.util.SOPTSharedPreferences
import org.sopt.soptandroidseminar.view.enqueueUtil
import org.sopt.soptandroidseminar.view.showToast

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToSignUpActivity()
        loginCheckEvent()
        autologinImageClickEvent()
        initAutoLoginEvent()
    }

    private fun goToSignUpActivity() {
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    private fun autologinImageClickEvent() {
        binding.btnAutoLogin.setOnClickListener {
            binding.btnAutoLogin.isSelected = !binding.btnAutoLogin.isSelected
            SOPTSharedPreferences.setAutoLogin(this, binding.btnAutoLogin.isSelected)
        }
    }

    private fun initAutoLoginEvent() {
        if (SOPTSharedPreferences.getAutoLogin(this)) {
            showToast("자동 로그인")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun loginCheckEvent() {
        binding.btnLogin.setOnClickListener {
            if (checkInputText()) {
                showToast("로그인 실패")
            } else {
                val requestLogin = RequestLogin(
                    email = binding.editId.text.toString(),
                    password = binding.editPw.text.toString()
                )
                loginRequest(requestLogin)
            }
        }
    }

    private fun loginRequest(requestLogin: RequestLogin) {
        val call = ApiServiceCreator.soptApiService.getLoginInfo(requestLogin)

        call.enqueueUtil(
            onSuccess = {
                it.data?.let { it1 -> goToHomeActivity(it1.name) }
            },
            onError = {
                showToast("로그인에 실패하였습니다.")
            }
        )
    }

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val id = it.data?.getStringExtra("userId")
            val pw = it.data?.getStringExtra("userPw")

            binding.editId.setText(id)
            binding.editPw.setText(pw)

        }
    }

    private fun checkInputText(): Boolean =
        binding.editId.text.isNullOrBlank() || binding.editPw.text.isNullOrBlank()

    private fun goToHomeActivity(name: String) {
        showToast("${name}님 환영합니다!")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}