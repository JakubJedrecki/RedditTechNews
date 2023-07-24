package com.jakub.reddittechnews.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.domain.repositories.TechNewsRepository
import com.jakub.domain.shared.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TechNewsRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing get() = _isRefreshing.value
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState.asStateFlow()

    fun getLatestNews() {
        viewModelScope.launch {
            _uiState.value = HomeUiState() // clear ui list
            _isRefreshing.value = true

            when (val result = repository.getTechNews()) {
                is ResultResponse.Success -> {
                    _uiState.value = HomeUiState(posts = result.data)
                }
                is ResultResponse.Error -> {
                    _uiState.value = HomeUiState(errorMsg = result.error.message, hasError = true)
                }
            }
            _isRefreshing.value = false
        }
    }

    init {
        getLatestNews()
    }
}