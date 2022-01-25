package org.sopt.soptandroidseminar.view.main.profile.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.api.data.response.ResponseUser
import org.sopt.soptandroidseminar.view.enqueueUtil

class FollowerListViewModel: ViewModel() {
    private val _followList = MutableLiveData<List<ResponseUser>>()
    val followList: LiveData<List<ResponseUser>> get() = _followList

    fun followingList() {
        ApiServiceCreator.githubApiService
            .getFollowingInfo()
            .enqueueUtil(
                onSuccess = {
                    _followList.value = it

                }
            )
    }
}