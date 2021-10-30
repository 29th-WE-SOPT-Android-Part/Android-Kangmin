package org.sopt.soptandroidseminar.api

import org.sopt.soptandroidseminar.api.data.response.ResponseRepoInfo
import org.sopt.soptandroidseminar.api.data.response.ResponseUserInfo
import retrofit2.Call
import retrofit2.http.GET

interface GithubApiService {
    @GET("users/kkk5474096/repos")
    fun reposForUser(): Call<List<ResponseRepoInfo>>

    @GET("users/kkk5474096")
    fun getUserInfo(): Call<ResponseUserInfo>

    @GET("users/kkk5474096/followers")
    fun getFollowingInfo(): Call<List<ResponseUserInfo>>
}