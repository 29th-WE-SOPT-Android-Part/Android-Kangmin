package org.sopt.soptandroidseminar.view.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.api.data.request.RequestLogin
import org.sopt.soptandroidseminar.view.enqueueUtil

class SignInViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _successLogIn = MutableLiveData<Boolean>()
    val successLogIn: LiveData<Boolean> get() = _successLogIn

    fun checkInputText(): Boolean =
        email.value.isNullOrBlank() || password.value.isNullOrBlank()

    fun loginRequest() {
        val requestLogin = RequestLogin(
            email = email.value.toString(),
            password = password.value.toString()
        )

        val call = ApiServiceCreator.soptApiService.getLoginInfo(requestLogin)

        call.enqueueUtil(
            onSuccess = {
                _name.value = it.data?.name
                _successLogIn.value = it.success
            },
            onError = {
                Log.d("NetworkTest", "error:${it}")
                _successLogIn.value = false
            }
        )
    }
}