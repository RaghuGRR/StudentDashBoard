package com.android.data.api

import com.android.data.dto.DashboardDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v0/b/user-contacts-ade83.appspot.com/o/student_dashboard.json?alt=media&token=0091b4c2-2ee2-4326-99cd-96d5312b34bd")
    suspend fun getUserInfo(): Response<DashboardDto?>
}