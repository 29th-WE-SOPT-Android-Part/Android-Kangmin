package org.sopt.soptandroidseminar.data.repository

import org.sopt.soptandroidseminar.data.service.GithubApiService
import org.sopt.soptandroidseminar.domain.GithubRepository
import org.sopt.soptandroidseminar.domain.entity.GithubFollow
import org.sopt.soptandroidseminar.domain.entity.GithubRepo
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubApiService
) : GithubRepository {
    override suspend fun followList(): List<GithubFollow>? {
        runCatching {
            githubService.followingInfo()
        }.fold({
            return it.map { response -> response.toFollowInfo() }
        }, { return null })
    }

    override suspend fun repoList(): List<GithubRepo>? {
        runCatching {
            githubService.reposForUser()
        }.fold({
            return it.map { response -> response.toRepoInfo() }
        }, { return null })
    }
}