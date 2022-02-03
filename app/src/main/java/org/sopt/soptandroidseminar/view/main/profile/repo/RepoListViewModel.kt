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
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val githubRepository: GithubRepository) : ViewModel() {
    private val _repoList = MutableLiveData<List<GithubRepo>>()
    val repoList: LiveData<List<GithubRepo>> get() = _repoList

    fun repoList() {
        viewModelScope.launch {
            runCatching {
                githubRepository.repoList()
            }.onSuccess {
                _repoList.value = it
            }.onFailure {
                Log.d("NetworkTest", "error:$it")
            }
        }
    }
}