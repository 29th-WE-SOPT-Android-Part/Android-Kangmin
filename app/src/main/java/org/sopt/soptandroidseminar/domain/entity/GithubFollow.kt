package org.sopt.soptandroidseminar.domain.entity

data class GithubFollow(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val repos_url: String
)
