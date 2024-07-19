package com.example.e_book_libruary_app.presentation.bottom_navigation_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.e_book_libruary_app.presentation.main.MainScreen
import com.example.e_book_libruary_app.presentation.search.SearchScreen
import com.example.e_book_libruary_app.presentation.sign_in.GoogleAuthUiClient
import com.example.e_book_libruary_app.util.Routes

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient
){
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN_SCREEN
    ) {
        composable(Routes.MAIN_SCREEN){
            MainScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                onNavigate = {
                    navController.navigate(it.route)
                },
                googleAuthUiClient = googleAuthUiClient
            )
        }

        composable(Routes.SEARCH_SCREEN){
            SearchScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
        }
    }
}