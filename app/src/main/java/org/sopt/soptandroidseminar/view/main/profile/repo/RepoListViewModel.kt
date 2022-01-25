package org.sopt.soptandroidseminar.view.main.profile.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.api.data.response.ResponseRepo
import org.sopt.soptandroidseminar.view.enqueueUtil

class RepoListViewModel : ViewModel() {
    private val _repoList = MutableLiveData<List<ResponseRepo>>()
    val repoList: LiveData<List<ResponseRepo>> get() = _repoList

    fun repoList() {
        ApiServiceCreator.githubApiService
            .reposForUser()
            .enqueueUtil(
                onSuccess = {
                    _repoList.value = it
                }
            )
    }
}
