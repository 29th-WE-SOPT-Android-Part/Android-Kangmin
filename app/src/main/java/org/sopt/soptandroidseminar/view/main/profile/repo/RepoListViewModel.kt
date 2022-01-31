package org.sopt.soptandroidseminar.view.main.profile.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.soptandroidseminar.data.service.GithubApiService
import org.sopt.soptandroidseminar.data.response.ResponseRepo
import retrofit2.await
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val service: GithubApiService) : ViewModel() {
    private val _repoList = MutableLiveData<List<ResponseRepo>>()
    val repoList: LiveData<List<ResponseRepo>> get() = _repoList

    fun repoList() {
        viewModelScope.launch {
            runCatching {
                service.reposForUser().await()
            }.onSuccess {
                _repoList.value = it
            }
        }
    }
}
