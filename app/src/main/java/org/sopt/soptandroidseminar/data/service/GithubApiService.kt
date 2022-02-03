package org.sopt.soptandroidseminar.data.service

import org.sopt.soptandroidseminar.data.response.ResponseRepo
import org.sopt.soptandroidseminar.data.response.ResponseUser
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubApiService {
    @Headers("Authorization: token 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")
    @GET("users/kkk5474096/repos")
    suspend fun reposForUser(): List<ResponseRepo>

    @Headers("Authorization: token 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")
    @GET("users/kkk5474096")
    suspend fun userInfo(): ResponseUser

    @Headers("Authorization: token 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")
    @GET("users/kkk5474096/followers")
    suspend fun followingInfo(): List<ResponseUser>
}