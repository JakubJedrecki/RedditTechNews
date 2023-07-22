package com.jakub.reddittechnews.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.domain.models.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing get() = _isRefreshing.value
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(posts = emptyList())
            }
//            _isRefreshing.value = true //pullRefresh has a bug and indicator doesn't go away
            withContext(Dispatchers.IO) {
                delay(2000)
                _uiState.update { state ->
                    state.copy(
                        posts = listOf(
                            Post(
                                "author 1",
                                Random.nextInt().toString(),
                                "title ${Random.nextInt()}",
                                "some image url",
                                "FlairText1"
                            ),
                            Post(
                                "author 6",
                                Random.nextInt().toString(),
                                "title ${Random.nextInt()}",
                                "some image url",
                                "FlairText6"
                            ),
                        )
                    )
                }
            }

            _isRefreshing.value = false
        }
    }

    init {
        refresh()
    }
}