package com.android.domain.usecase

import com.android.domain.repository.LoginRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    fun isUserLoggedIn() = loginRepository.isUserLoggedIn()

    suspend fun login(schoolId: String, studentId: String) = loginRepository.login(schoolId, studentId)
    suspend fun logOut() = loginRepository.logOut()
}