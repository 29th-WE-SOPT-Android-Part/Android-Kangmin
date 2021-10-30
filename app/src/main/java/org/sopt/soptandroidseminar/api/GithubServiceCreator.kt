package org.sopt.soptandroidseminar.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubServiceCreator {
    private val gson = GsonBuilder().setLenient().create()
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService: GithubApiService = retrofit.create(GithubApiService::class.java)
}