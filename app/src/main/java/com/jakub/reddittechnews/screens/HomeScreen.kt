package com.jakub.reddittechnews.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakub.domain.models.Post
import com.jakub.reddittechnews.ui.theme.Purple40
import com.jakub.ui.components.PostComponent

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    val items = listOf(
        Post("author 1", "timestamp1", "title1", "some image url", "FlairText1"),
        Post("author 2", "timestamp2", "title2", "some image url", "FlairText2"),
        Post("author 3", "timestamp3", "title3", "some image url", "FlairText3"),
        Post("author 4", "timestamp4", "title4", "some image url", "FlairText4"),
        Post("author 5", "timestamp5", "title5", "some image url", "FlairText5"),
        Post("author 6", "timestamp6", "title6", "some image url", "FlairText6"),
        Post("author 6", "timestamp6", "title6", "some image url", "FlairText6"),
        Post("author 6", "timestamp6", "title6", "some image url", "FlairText6"),
        Post("author 6", "timestamp6", "title6", "some image url", "FlairText6"),
        Post("author 6", "timestamp6", "title6", "some image url", "FlairText6"),
        Post("author 6", "timestamp6", "title6", "some image url", "FlairText6"),
        Post("author 6", "timestamp6", "title6", "some image url", "FlairText6"),
    )

    Scaffold(topBar = {
        SmallTopAppBar(
            title = { Text(text = "Technology") },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            LazyColumn {
                itemsIndexed(items) { index, item ->
                    PostComponent(
                        position = index + 1,
                        author = item.author,
                        title = item.title,
                        timestamp = item.timestamp,
                        label = item.linkFlairText,
                        imageUrl = item.imageUrl
                    )
                }
            }
        }
    }
}