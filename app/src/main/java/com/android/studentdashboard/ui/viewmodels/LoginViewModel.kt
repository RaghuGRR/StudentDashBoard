package com.android.studentdashboard.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.usecase.LoginUseCase
import com.android.domain.util.DataResource
import com.android.studentdashboard.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel(){

    private val _isUserLoggedIn = MutableStateFlow(loginUseCase.isUserLoggedIn())
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val loginState: StateFlow<UiState<Unit>> = _loginState

    fun login(schoolId: String, studentId: String){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.login(schoolId, studentId).collectLatest {
                when(it){
                    is DataResource.Error -> {
                        val message = mapErrorToMessage(it.exception)
                        _loginState.value = UiState.Error(message)
                    }
                    DataResource.Loading -> {
                        _loginState.value = UiState.Loading
                    }
                    is DataResource.Success<Unit> -> {
                        _loginState.value = UiState.Success(Unit)
                    }
                }
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            loginUseCase.logOut()
            _isUserLoggedIn.value = false
        }
    }

    private fun mapErrorToMessage(e: Throwable): String {
        return when {
            e.message?.contains("network", ignoreCase = true) == true ->
                "Network error. Please check your internet."
            e.message?.contains("password", ignoreCase = true) == true ->
                "Invalid credentials. Please check School ID and Student ID."
            else -> e.localizedMessage ?: "An unexpected error occurred."
        }
    }

}
