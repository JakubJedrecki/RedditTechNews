package com.jakub.reddittechnews.navigation

enum class NewsScreens {
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): NewsScreens =
            when(route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}