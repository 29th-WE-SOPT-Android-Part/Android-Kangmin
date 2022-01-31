package org.sopt.soptandroidseminar.view.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.data.request.RequestLogin
import org.sopt.soptandroidseminar.domain.AuthRepository
import org.sopt.soptandroidseminar.view.signup.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val loginRepository: AuthRepository) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _successLogIn = SingleLiveEvent<Unit>()
    val successLogIn: LiveData<Unit> get() = _successLogIn

    private val _failureLogIn = SingleLiveEvent<Unit>()
    val failureLogIn: LiveData<Unit> get() = _failureLogIn

    val isInputBlank: Boolean
        get() = email.value.isNullOrBlank() || password.value.isNullOrBlank()


    fun login(id: String, pw: String) {
        viewModelScope.launch {
            runCatching { loginRepository.login(id, pw) }
                .onSuccess {
                    if (it !== null) _successLogIn.call()
                    else _failureLogIn.call()
                }
                .onFailure { Log.e("error", "asa", it) }
        }
    }
}