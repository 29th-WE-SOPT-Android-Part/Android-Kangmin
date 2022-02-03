package org.sopt.soptandroidseminar.domain

import org.sopt.soptandroidseminar.domain.entity.GithubFollow
import org.sopt.soptandroidseminar.domain.entity.GithubRepo

interface GithubRepository {
    suspend fun followList(): List<GithubFollow>?
    suspend fun repoList(): List<GithubRepo>?
}