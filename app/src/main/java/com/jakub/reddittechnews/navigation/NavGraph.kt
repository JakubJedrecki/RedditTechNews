package com.jakub.reddittechnews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jakub.reddittechnews.features.details.PostDetails
import com.jakub.reddittechnews.features.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NewsScreens.HomeScreen.name,
    ) {

        composable(NewsScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(
            NewsScreens.DetailsScreen.name + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            PostDetails(
                navController = navController,
                backStackEntry.arguments?.getString("id")
            )
        }
    }
}