package org.sopt.soptandroidseminar.view.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.api.data.request.RequestLogin
import org.sopt.soptandroidseminar.api.data.SOPTSharedPreferences
import org.sopt.soptandroidseminar.view.enqueueUtil
import org.sopt.soptandroidseminar.view.signup.SingleLiveEvent

class SignInViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _successLogIn = SingleLiveEvent<Unit>()
    val successLogIn: LiveData<Unit> get() = _successLogIn

    val isInputBlank: Boolean
        get() = email.value.isNullOrBlank() || password.value.isNullOrBlank()


    fun login() {
        val requestLogin = RequestLogin(
            email = email.value.toString(),
            password = password.value.toString()
        )

        ApiServiceCreator.soptApiService
            .getLoginInfo(requestLogin)
            .enqueueUtil(
                onSuccess = {
                    it.data?.name?.let { SOPTSharedPreferences.setName(it) }
                    _successLogIn.call()
                },
                onError = {
                    Log.d("NetworkTest", "error:${it}")
                })
    }
}