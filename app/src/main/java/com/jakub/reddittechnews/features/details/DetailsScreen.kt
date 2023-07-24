package com.jakub.reddittechnews.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jakub.domain.models.Post
import com.jakub.reddittechnews.ui.theme.Purple40
import com.jakub.ui.components.ErrorView
import com.jakub.ui.components.PostDetailsComponent

@Composable
fun PostDetailsScreen(
    navController: NavController
) {
    val post = navController.previousBackStackEntry?.savedStateHandle?.get<Post>("post")

    PostDetailsContent(post)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailsContent(
    post: Post?
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Technology") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            if (post == null) {
                ErrorView(errorMessage = "something went wrong")
            } else {
                PostDetailsComponent(
                    author = post.author,
                    title = post.title,
                    timestamp = post.timestamp,
                    label = post.linkFlairText,
                    imageUrl = post.imageUrl,
                    upVotes = post.upVotes,
                    downVotes = post.downVotes,
                    numComments = post.numComments
                )
            }
        }
    }
}