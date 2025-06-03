package com.aria.apiconsumption.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aria.apiconsumption.ui.openlibrary.BookDetailScreen
import com.aria.apiconsumption.ui.openlibrary.OpenLibraryScreen
import com.aria.apiconsumption.ui.screen.HomeScreen
import com.aria.apiconsumption.ui.screen.MainScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("saludo") { MainScreen() }

        // ✅ Pasar navController como argumento
        composable("public") {
            OpenLibraryScreen(navController = navController)
        }

        // ✅ Pantalla de detalle con argumentos dinámicos
        composable(
            "public/detail/{title}/{author}/{coverId}/{key}",
            arguments = listOf(
                navArgument("title") { nullable = true },
                navArgument("author") { nullable = true },
                navArgument("coverId") { nullable = true },
                navArgument("key") { nullable = true }
            )
        ) { backStackEntry ->
            BookDetailScreen(
                title = backStackEntry.arguments?.getString("title"),
                author = backStackEntry.arguments?.getString("author"),
                coverId = backStackEntry.arguments?.getString("coverId"),
                workKey = backStackEntry.arguments?.getString("key")
            )
        }
    }
}