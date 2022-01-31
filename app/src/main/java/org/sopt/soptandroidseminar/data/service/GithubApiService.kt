package org.sopt.soptandroidseminar.data.service

import org.sopt.soptandroidseminar.data.response.ResponseRepo
import org.sopt.soptandroidseminar.data.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET

interface GithubApiService {
    @GET("users/kkk5474096/repos")
    fun reposForUser(): Call<List<ResponseRepo>>

    @GET("users/kkk5474096")
    fun getUserInfo(): Call<ResponseUser>

    @GET("users/kkk5474096/followers")
    fun getFollowingInfo(): Call<List<ResponseUser>>
}