package org.sopt.soptandroidseminar.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ApiServiceCreator {
    private val gson = GsonBuilder().setLenient().create()
    private const val BASE_URL_SOPT = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private const val BASE_URL_GITHUB = "https://api.github.com/"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SOPT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun provideOkHttpClient(
        interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()

            proceed(newRequest)
        }
    }

    val soptApiService: SoptApiService = soptRetrofit.create(SoptApiService::class.java)
    val githubApiService: GithubApiService = githubRetrofit.create(GithubApiService::class.java)
}