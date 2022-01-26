package org.sopt.soptandroidseminar.view.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.view.enqueueUtil
import org.sopt.soptandroidseminar.view.signup.SingleLiveEvent

class ProfileViewModel : ViewModel() {

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    private val _followFragment = SingleLiveEvent<Unit>()
    val followFragment: LiveData<Unit> get() = _followFragment

    private val _repoFragment = SingleLiveEvent<Unit>()
    val repoFragment: LiveData<Unit> get() = _repoFragment

    fun profileImage() {
        ApiServiceCreator.githubApiService
            .getUserInfo()
            .enqueueUtil(
                onSuccess = {
                    _imageUrl.value = it.avatar_url
                }
            )
    }

    fun followFragment() {
        _followFragment.call()
    }

    fun repoFragment() {
        _repoFragment.call()
    }



}


