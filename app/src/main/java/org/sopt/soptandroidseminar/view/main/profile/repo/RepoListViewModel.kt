package org.sopt.soptandroidseminar.view.main.profile.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.domain.GithubRepository
import org.sopt.soptandroidseminar.domain.entity.GithubRepo
import org.sopt.soptandroidseminar.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val githubRepository: GithubRepository) : ViewModel() {
    private val _repoList = MutableLiveData<List<GithubRepo>>()
    val repoList: LiveData<List<GithubRepo>> get() = _repoList

    private val _serverConnect = SingleLiveEvent<Unit>()
    val serverConnect: LiveData<Unit> get() = _serverConnect

    fun repoList() {
        viewModelScope.launch {
            runCatching {
                githubRepository.repoList()
            }.onSuccess {
                if (it == null) {
                    _serverConnect.call()
                } else {
                    _repoList.value = it
                }
            }.onFailure {
                Log.d("NetworkTest", "error:$it")
            }
        }
    }
}