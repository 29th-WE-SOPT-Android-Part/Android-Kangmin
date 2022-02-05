package org.sopt.soptandroidseminar.data.service

import org.sopt.soptandroidseminar.data.response.ResponseRepo
import org.sopt.soptandroidseminar.data.response.ResponseUser
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubApiService {
    @GET("users/kkk5474096/repos")
    suspend fun reposForUser(): List<ResponseRepo>

    @GET("users/kkk5474096")
    suspend fun userInfo(): ResponseUser

    @GET("users/kkk5474096/followers")
    suspend fun followingInfo(): List<ResponseUser>
}