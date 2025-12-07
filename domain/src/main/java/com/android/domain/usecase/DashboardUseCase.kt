package com.android.domain.usecase

import com.android.domain.repository.DashboardRepository
import javax.inject.Inject


class DashboardUseCase @Inject constructor(private val dashboardRepository: DashboardRepository) {

    suspend fun getDashboardData() = dashboardRepository.getDashboardData()
}