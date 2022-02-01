package org.sopt.soptandroidseminar.view.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.domain.AuthRepository
import org.sopt.soptandroidseminar.util.Event
import org.sopt.soptandroidseminar.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val loginRepository: AuthRepository) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _logIn = MutableLiveData<Event<Int>>()
    val logIn: LiveData<Event<Int>> get() = _logIn

    val isInputBlank: Boolean
        get() = email.value.isNullOrBlank() || password.value.isNullOrBlank()

    fun login(id: String, pw: String) {
        viewModelScope.launch {
            runCatching { loginRepository.login(id, pw) }
                .onSuccess {
                    if (it !== null) _logIn.value = Event(LOGIN_SUCCESS)
                    else _logIn.value = Event(LOGIN_FAILURE)
                }
                .onFailure {
                    Log.e("error", "asa", it)
                    _logIn.value = Event(SERVER_FAILURE)
                }
        }
    }

    companion object {
        const val LOGIN_SUCCESS = 1
        const val LOGIN_FAILURE = 2
        const val SERVER_FAILURE = 3
    }
}