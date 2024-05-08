package com.example.moviecompose.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecompose.ui.screens.Home
import com.example.moviecompose.ui.screens.MovieList

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            Home{
                navController.navigate("MovieList")
            }
        }

        composable("MovieList"){
            MovieList()
        }
    }
}