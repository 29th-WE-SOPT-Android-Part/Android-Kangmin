package org.sopt.soptandroidseminar.data.service

import org.sopt.soptandroidseminar.data.request.RequestLogin
import org.sopt.soptandroidseminar.data.request.RequestSignUp
import org.sopt.soptandroidseminar.data.response.ResponseLogin
import org.sopt.soptandroidseminar.data.response.ResponseSignUp
import org.sopt.soptandroidseminar.data.response.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptApiService {
    @POST("user/signup")
    suspend fun postSignUp(@Body body: RequestSignUp): BaseResponse<ResponseSignUp>

    @POST("user/login")
    suspend fun postLogin(@Body body: RequestLogin): BaseResponse<ResponseLogin>
}