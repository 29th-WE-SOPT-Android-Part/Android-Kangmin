package org.sopt.soptandroidseminar.view.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.api.data.request.RequestSignUp
import org.sopt.soptandroidseminar.databinding.ActivitySignUpBinding
import org.sopt.soptandroidseminar.view.enqueueUtil
import org.sopt.soptandroidseminar.view.showToast

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
                signUpRequest()
            }
        }
    }

    private fun signUpRequest() {
        val requestSignUpData = RequestSignUp(
            email = binding.editId.text.toString(),
            name = binding.editName.text.toString(),
            password = binding.editPw.text.toString()
        )
        val call = ApiServiceCreator.soptApiService.postSignUp(requestSignUpData)

        call.enqueueUtil(
            onSuccess = {
                it.data?.let { it1 -> successSignUp(it1.name) }
            },
            onError = {
                showToast("회원가입에 실패하였습니다.")
            }
        )
    }

    private fun checkInputText(): Boolean =
        binding.editName.text.isNullOrBlank() || binding.editId.text.isNullOrBlank() || binding.editPw.text.isNullOrBlank()

    private fun successSignUp(name: String) {
        showToast("${name}님 회원가입을 성공하셨습니다.")
        val intent = Intent().apply {
            putExtra("userId", binding.editId.text.toString())
            putExtra("userPw", binding.editPw.text.toString())
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}