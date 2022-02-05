package org.sopt.soptandroidseminar.view.main.profile.follow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.domain.GithubRepository
import org.sopt.soptandroidseminar.domain.entity.GithubFollow
import org.sopt.soptandroidseminar.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class FollowerListViewModel @Inject constructor(private val githubRepository: GithubRepository) : ViewModel() {
    private val _followList = MutableLiveData<List<GithubFollow>>()
    val followList: LiveData<List<GithubFollow>> get() = _followList

    private val _serverConnect = SingleLiveEvent<Unit>()
    val serverConnect: LiveData<Unit> get() = _serverConnect

    fun followingList() {
        viewModelScope.launch {
            runCatching {
                githubRepository.followList()
            }.onSuccess {
                if (it == null) {
                    _serverConnect.call()
                } else {
                    _followList.value = it
                }
            }.onFailure {
                Log.d("NetworkTest", "error:$it")
            }
        }
    }
}