package com.jakub.reddittechnews.features.home

import com.jakub.domain.models.Post

data class HomeUiState (
    val posts: List<Post> = listOf(),
    val hasError: Boolean = false,
    val errorMsg: String = ""
)
