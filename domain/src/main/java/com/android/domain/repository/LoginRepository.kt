package com.android.domain.repository

import com.android.domain.util.DataResource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun isUserLoggedIn(): Boolean

    suspend fun login(schoolId: String, studentId: String): Flow<DataResource<Unit>>
    suspend fun logOut()
}