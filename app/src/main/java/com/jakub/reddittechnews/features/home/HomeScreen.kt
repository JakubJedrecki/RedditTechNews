package com.jakub.reddittechnews.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jakub.domain.models.Post
import com.jakub.reddittechnews.navigation.NewsScreens
import com.jakub.reddittechnews.ui.theme.Purple40
import com.jakub.ui.components.ErrorView
import com.jakub.ui.components.PostComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
            HomeContent(
                navController,
                uiState,
                isRefreshing = viewModel.isRefreshing
            ) { viewModel.getLatestNews() }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    navController: NavController,
    uiState: HomeUiState,
    isRefreshing: Boolean,
    getNews: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            getNews()
        })

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
    ) {
        if (uiState.hasError) {
            ErrorView(errorMessage = uiState.errorMsg)
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(uiState.posts) { index, item ->
                    PostComponent(
                        position = index + 1,
                        author = item.author,
                        title = item.title,
                        timestamp = item.timestamp,
                        label = item.linkFlairText,
                        imageUrl = item.imageUrl
                    ) {
                        //not the best approach to pass data but given the time running out
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "post",
                            value = item
                        )
                        navController.navigate(route = NewsScreens.DetailsScreen.name)
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = isRefreshing, state = pullRefreshState, Modifier.align(
                    Alignment.TopCenter
                )
            )
        }
    }
}