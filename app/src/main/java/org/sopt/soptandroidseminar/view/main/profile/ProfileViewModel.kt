package org.sopt.soptandroidseminar.view.main.profile

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.view.enqueueUtil
import retrofit2.await

class ProfileViewModel : ViewModel() {

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    private val _fragment = MutableLiveData<Boolean>()
    val fragment: LiveData<Boolean> get() = _fragment

    fun profileImage() {
        ApiServiceCreator.githubApiService
            .getUserInfo()
            .enqueueUtil(
                onSuccess = {
                    _imageUrl.value = it.avatar_url

                }
            )
    }

    fun setFragment() {
        _fragment.value = true
    }

    fun setFalseFragment() {
        _fragment.value = false
    }


}


