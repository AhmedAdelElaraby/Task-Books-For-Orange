package com.example.books.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.books.ui.screens.detailsBooks.DetailsBooksScreen
import com.example.books.ui.screens.home.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun AppNavigation() {
    val navController = rememberNavController() // إنشاء الـ NavController

    NavHost(navController = navController, startDestination = StateScreens.HomeScreen.route) {
        composable(StateScreens.HomeScreen.route) { HomeScreen(navController) }

        composable(StateScreens.DetailsBooksScreen.route,
           arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: "Unknown"
            DetailsBooksScreen(navController, bookId)
        }
    }
}