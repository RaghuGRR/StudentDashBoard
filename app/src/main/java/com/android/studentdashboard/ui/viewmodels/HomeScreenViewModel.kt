package com.android.studentdashboard.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.DashboardModel
import com.android.domain.usecase.DashboardUseCase
import com.android.domain.util.DataResource
import com.android.studentdashboard.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dashboardUseCase: DashboardUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow<UiState<DashboardModel>>(UiState.Loading)
    val homeState = _homeState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _homeState.value = UiState.Loading
            dashboardUseCase.getDashboardData()
                .collectLatest { result ->
                    _homeState.value = when(result) {
                        is DataResource.Success<DashboardModel> -> UiState.Success(result.data)
                        is DataResource.Error -> UiState.Error(result.exception.message ?: "Error")
                        is DataResource.Loading -> UiState.Loading
                    }
                }
        }
    }
}