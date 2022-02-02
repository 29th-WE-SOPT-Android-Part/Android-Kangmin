package org.sopt.soptandroidseminar.domain

import org.sopt.soptandroidseminar.domain.entity.Auth

interface AuthRepository {
    suspend fun login(id: String, pw: String): Auth?
    suspend fun signUp(name:String, id: String, pw: String): Boolean
}