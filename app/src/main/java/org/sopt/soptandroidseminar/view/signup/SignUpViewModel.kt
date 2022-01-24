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

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean> get() = _signUpSuccess

    fun checkInputText(): Boolean =
        email.value.isNullOrBlank() || name.value.isNullOrBlank() || password.value.isNullOrBlank()

    fun signUpRequest() {
        val requestSignUpData = RequestSignUp(
            email = email.value.toString(),
            name = name.value.toString(),
            password = name.value.toString()
        )

        val call = ApiServiceCreator.soptApiService.postSignUp(requestSignUpData)

        call.enqueueUtil(
            onSuccess = {
                _signUpSuccess.value = it.success
            },
            onError = {
                Log.d("NetworkTest", "error:${it}")
                _signUpSuccess.value = false
            }
        )
    }
}