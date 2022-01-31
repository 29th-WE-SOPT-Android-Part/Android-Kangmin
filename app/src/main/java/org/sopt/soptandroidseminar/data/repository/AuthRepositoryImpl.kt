package org.sopt.soptandroidseminar.data.repository

import org.sopt.soptandroidseminar.data.SoptDataStore
import org.sopt.soptandroidseminar.data.request.RequestLogin
import org.sopt.soptandroidseminar.data.request.RequestSignUp
import org.sopt.soptandroidseminar.data.response.ResponseLogin
import org.sopt.soptandroidseminar.data.service.SoptApiService
import org.sopt.soptandroidseminar.domain.AuthRepository
import org.sopt.soptandroidseminar.domain.entity.Auth
import retrofit2.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: SoptApiService,
    private val sharedPreferences: SoptDataStore
) : AuthRepository {
    override suspend fun login(id: String, pw: String): Auth? {
        val result = runCatching {
            service.postLogin(RequestLogin(id, pw)).await()
        }.onSuccess {
            it.data?.name?.let { sharedPreferences.setName(it) }
        }.getOrNull()

        return if (result != null) result.data?.toEntity() else null
    }

    override suspend fun signUp(name: String, id: String, pw: String) : Boolean{
        runCatching {
            service.postSignUp(RequestSignUp(name, id, pw)).await()
        }.fold({
            return true
        },{
            return false
        }) }

    private fun ResponseLogin.toEntity() = Auth(email, id, name)
}