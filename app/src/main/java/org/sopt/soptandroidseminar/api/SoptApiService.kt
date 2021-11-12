package org.sopt.soptandroidseminar.api

import org.sopt.soptandroidseminar.api.data.request.RequestLogin
import org.sopt.soptandroidseminar.api.data.request.RequestSignUp
import org.sopt.soptandroidseminar.api.data.response.ResponseSignIn
import org.sopt.soptandroidseminar.api.data.response.ResponseSignUp
import org.sopt.soptandroidseminar.api.data.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptApiService {
    @POST("user/signup")
    fun postSignUp(@Body body: RequestSignUp): Call<ResponseWrapper<ResponseSignUp>>

    @POST("user/login")
    fun getLoginInfo(@Body body: RequestLogin): Call<ResponseWrapper<ResponseSignIn>>
}