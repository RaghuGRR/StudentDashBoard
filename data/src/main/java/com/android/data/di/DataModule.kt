package com.android.data.di

import android.app.Application
import com.android.data.SessionManager
import com.android.data.api.ApiService
import com.android.data.repositoryImpl.DashboardRepositoryImpl
import com.android.data.repositoryImpl.LoginRepositoryImpl
import com.android.domain.repository.DashboardRepository
import com.android.domain.repository.LoginRepository
import com.android.domain.usecase.DashboardUseCase
import com.android.domain.usecase.LoginUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideSessionManager(
        application: Application
    ): SessionManager {
        return SessionManager(application)
    }


    @Provides
    @Singleton
    fun provideLoginRepository(firebaseAuth: FirebaseAuth,sessionManager: SessionManager): LoginRepository {
        return LoginRepositoryImpl(firebaseAuth,sessionManager)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository)
    }


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDashboardRepository(apiService: ApiService): DashboardRepository {
        return DashboardRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDashboardUseCase(dashboardRepository: DashboardRepository): DashboardUseCase {
        return DashboardUseCase(dashboardRepository)
    }

}