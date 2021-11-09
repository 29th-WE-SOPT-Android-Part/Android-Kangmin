package org.sopt.soptandroidseminar.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.soptandroidseminar.api.SoptServiceCreator
import org.sopt.soptandroidseminar.api.data.request.RequestLogin
import org.sopt.soptandroidseminar.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToSignUpActivity()
        loginCheckEvent()
    }

    private fun goToSignUpActivity() {
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
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
        val call = SoptServiceCreator.apiService.getLoginInfo(requestLogin)

        call.enqueueUtil(
            onSuccess = {
                goToHomeActivity(it.data.name)
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