package com.android.domain.repository

import com.android.domain.model.DashboardModel
import com.android.domain.util.DataResource
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    suspend fun getDashboardData(): Flow<DataResource<DashboardModel>>
}