package com.android.domain.util

sealed class DataResource<out T> {
    data class Success<out T>(val data: T) : DataResource<T>()
    data class Error(val exception: Throwable) : DataResource<Nothing>()
    data object Loading : DataResource<Nothing>()
}