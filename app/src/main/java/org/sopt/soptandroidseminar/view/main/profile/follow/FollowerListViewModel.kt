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
import javax.inject.Inject

@HiltViewModel
class FollowerListViewModel @Inject constructor(private val githubRepository: GithubRepository) : ViewModel() {
    private val _followList = MutableLiveData<List<GithubFollow>>()
    val followList: LiveData<List<GithubFollow>> get() = _followList

    fun followingList() {
        viewModelScope.launch {
            runCatching {
                githubRepository.followList()
            }.onSuccess {
                _followList.value = it
            }.onFailure {
                Log.d("NetworkTest", "error:$it")
            }
        }
    }
}