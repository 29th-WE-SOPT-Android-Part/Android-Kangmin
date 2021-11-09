package org.sopt.soptandroidseminar.api.data.response

data class ResponseSignUp(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val id: Int,
        val name: String,
        val password: Int
    )
}