package org.sopt.soptandroidseminar.data

enum class LoginState(
    private val stateCode: Int,
    private val message: String
) {
    SUCCESS(200, "로그인에 성공하셨습니다"),
    FAIL(400, "아이디/비밀번호를 확인해주세요"),
    DISCONNECTION(404, "서버통신에 실패하였습니다.")
}