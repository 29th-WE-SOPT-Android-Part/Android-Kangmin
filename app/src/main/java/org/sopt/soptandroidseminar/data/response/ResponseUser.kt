package org.sopt.soptandroidseminar.data.response

import org.sopt.soptandroidseminar.domain.entity.GithubFollow

data class ResponseUser(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val repos_url: String
) {
    fun toFollowInfo(): GithubFollow {
        return GithubFollow(id, login, avatar_url, repos_url)
    }
}

