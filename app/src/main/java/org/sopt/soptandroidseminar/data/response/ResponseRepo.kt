package org.sopt.soptandroidseminar.data.response

import org.sopt.soptandroidseminar.domain.entity.GithubFollow
import org.sopt.soptandroidseminar.domain.entity.GithubRepo

data class ResponseRepo(
    val name: String,
    val description: String?,
    val language: String?
) {
    fun toRepoInfo(): GithubRepo {
        return GithubRepo(name, description, language)
    }
}