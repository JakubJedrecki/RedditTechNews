package com.jakub.reddittechnews.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PostDetails(
    navController: NavController,
    postId: String?
) {
    Column {
        Text(text = postId ?: "")
    }
}