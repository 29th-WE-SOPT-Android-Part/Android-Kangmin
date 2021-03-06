package org.sopt.soptandroidseminar.view.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.ActivitySignUpBinding
import org.sopt.soptandroidseminar.view.showToast

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        signUpButtonClickEvent()
        observeSignUp()
    }

    private fun signUpButtonClickEvent() {
        binding.btnSignUpSuccess.setOnClickListener {
            if (viewModel.isInputBlank) {
                showToast("입력되지 않은 정보가 있습니다")
            } else {
                viewModel.signUp(binding.editName.text.toString(), binding.editId.text.toString(), binding.editPw.toString())
            }
        }
    }

    private fun observeSignUp() {
        viewModel.signUpSuccess.observe(this) {
            successSignUp()
        }
        viewModel.signUpFailure.observe(this) {
            showToast("회원가입 실패")
        }
    }

    private fun successSignUp() {
        showToast("${viewModel.name.value}님 회원가입을 성공하셨습니다.")
        val intent = Intent().apply {
            putExtra("userId", binding.editId.text.toString())
            putExtra("userPw", binding.editPw.text.toString())
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}