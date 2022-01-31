package org.sopt.soptandroidseminar.view.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.data.service.GithubApiService
import org.sopt.soptandroidseminar.view.signup.SingleLiveEvent
import retrofit2.await
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val service: GithubApiService) : ViewModel() {

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    private val _followFragment = SingleLiveEvent<Unit>()
    val followFragment: LiveData<Unit> get() = _followFragment

    private val _repoFragment = SingleLiveEvent<Unit>()
    val repoFragment: LiveData<Unit> get() = _repoFragment

    fun profileImage() {
        viewModelScope.launch {
            runCatching {
                service.getUserInfo().await()
            }.onSuccess {
                _imageUrl.value = it.avatar_url
            }
        }
    }

    fun followFragment() {
        _followFragment.call()
    }

    fun repoFragment() {
        _repoFragment.call()
    }



}


