package com.jakub.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val author: String,
    val timestamp: String,
    val title: String,
    val imageUrl: String,
    val linkFlairText: String,
    val upVotes: Int = 0,
    val downVotes: Int = 0,
    val numComments: Int = 0
): Parcelable
