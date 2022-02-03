package org.sopt.soptandroidseminar.data.response

import org.sopt.soptandroidseminar.domain.entity.Auth

data class ResponseLogin(
    val email: String,
    val id: Int,
    val name: String
) {
    fun toAuth(statusCode: Int) = Auth(email, id, name, statusCode)
}
