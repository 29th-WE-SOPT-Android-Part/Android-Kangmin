package org.sopt.soptandroidseminar.view.main.profile.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.api.GithubApiService
import org.sopt.soptandroidseminar.api.data.response.ResponseUser
import retrofit2.await
import javax.inject.Inject

@HiltViewModel
class FollowerListViewModel @Inject constructor(private val apiService: GithubApiService) : ViewModel() {
    private val _followList = MutableLiveData<List<ResponseUser>>()
    val followList: LiveData<List<ResponseUser>> get() = _followList

    fun followingList() {
        viewModelScope.launch {
            runCatching {
                apiService.getFollowingInfo().await()
            }.onSuccess {
                _followList.value = it
            }
        }
    }
}