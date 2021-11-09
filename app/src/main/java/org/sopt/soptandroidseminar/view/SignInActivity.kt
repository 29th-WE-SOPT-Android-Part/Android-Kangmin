package org.sopt.soptandroidseminar.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.soptandroidseminar.R
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
                goToHomeActivity()
            }
        }
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

    private fun goToHomeActivity() {
        showToast("이강민님 환영합니다!")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}