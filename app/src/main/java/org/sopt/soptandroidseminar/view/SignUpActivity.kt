package org.sopt.soptandroidseminar.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.soptandroidseminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpButtonClickEvent()
    }

    private fun signUpButtonClickEvent() {
        binding.btnSignUpSuccess.setOnClickListener {
            if (checkInputText()) {
                showToast("입력되지 않은 정보가 있습니다")
            } else {
                successSignUp()
            }
        }
    }

    private fun checkInputText(): Boolean {
        return binding.editName.text.isNullOrBlank() || binding.editId.text.isNullOrBlank() || binding.editPw.text.isNullOrBlank()
    }

    private fun successSignUp() {
        val intent = Intent()
        intent.putExtra("userId", binding.editId.text.toString())
        intent.putExtra("userPw", binding.editPw.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}