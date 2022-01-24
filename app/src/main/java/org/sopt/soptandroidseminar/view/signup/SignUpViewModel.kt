package org.sopt.soptandroidseminar.view.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.api.data.request.RequestSignUp
import org.sopt.soptandroidseminar.view.enqueueUtil

class SignUpViewModel : ViewModel() {

    val email = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _signUpSuccess = SingleLiveEvent<Unit>()
    val signUpSuccess: LiveData<Unit> get() = _signUpSuccess

    val isInputBlank: Boolean
        get() = email.value.isNullOrBlank() || name.value.isNullOrBlank() || password.value.isNullOrBlank()

    fun signUp() {
        val requestSignUpData = RequestSignUp(
            email = email.value.toString(),
            name = name.value.toString(),
            password = name.value.toString()
        )

        ApiServiceCreator.soptApiService
            .postSignUp(requestSignUpData)
            .enqueueUtil(
                onSuccess = {
                    _signUpSuccess.call()
                },
                onError = {
                    Log.d("NetworkTest", "error:${it}")
                }
            )
    }
}