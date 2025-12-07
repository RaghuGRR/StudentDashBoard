package com.android.data.repositoryImpl

import android.util.Log
import com.android.data.api.ApiService
import com.android.data.mappers.toDomain
import com.android.domain.model.DashboardModel
import com.android.domain.repository.DashboardRepository
import com.android.domain.util.DataResource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

private const val TAG = "DashboardRepositoryImpl"

class DashboardRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DashboardRepository {

    override suspend fun getDashboardData(): Flow<DataResource<DashboardModel>> = callbackFlow {
        trySend(DataResource.Loading)
        Log.d(TAG, "getDashBoardInfo: invoke()")

        try {
            val response = apiService.getUserInfo()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val domainModel = body.toDomain()
                trySend(DataResource.Success(domainModel))
            } else {
                val errorMsg = response.errorBody()?.string() ?: response.message()
                trySend(DataResource.Error(Throwable(errorMsg)))
            }

        } catch (e: Exception) {
            Log.e(TAG, "getDashBoardInfo Error", e)
            trySend(DataResource.Error(e))
        }
        close()

        awaitClose {
            Log.d(TAG, "getDashBoardInfo: Flow closed")
        }


    }
}