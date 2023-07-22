package com.jakub.reddittechnews.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakub.reddittechnews.ui.theme.Purple40
import com.jakub.ui.components.PostComponent

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Technology") },
            colors = topAppBarColors(containerColor = Purple40)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            HomeContent()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent() {
    val viewModel = HomeViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.isRefreshing,
        onRefresh = {
            viewModel.refresh()
        })

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(uiState.posts) { index, item ->
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

        PullRefreshIndicator(
            refreshing = viewModel.isRefreshing, state = pullRefreshState, Modifier.align(
                Alignment.TopCenter
            )
        )
    }
}