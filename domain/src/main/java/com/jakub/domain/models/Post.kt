package com.jakub.domain.models

data class Post(
    val author: String,
    val timestamp: String,
    val title: String,
    val imageUrl: String,
    val linkFlairText: String
)
