package org.sopt.soptandroidseminar.view.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.domain.AuthRepository
import org.sopt.soptandroidseminar.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val email = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _signUpSuccess = SingleLiveEvent<Unit>()
    val signUpSuccess: LiveData<Unit> get() = _signUpSuccess

    private val _signUpFailure = SingleLiveEvent<Unit>()
    val signUpFailure: LiveData<Unit> get() = _signUpFailure

    val isInputBlank: Boolean
        get() = email.value.isNullOrBlank() || name.value.isNullOrBlank() || password.value.isNullOrBlank()

    fun signUp(name:String, id:String, pw:String) {
        viewModelScope.launch {
            runCatching {
                authRepository.signUp(name, id, pw)
            }.onSuccess {
                if(it) _signUpSuccess.call()
                else _signUpFailure.call()
            }.onFailure {
                Log.d("NetworkTest", "error:${it}")
            }
        }
    }
}