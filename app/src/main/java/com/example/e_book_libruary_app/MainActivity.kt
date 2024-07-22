package com.example.e_book_libruary_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.e_book_libruary_app.presentation.book_card.BookCardScreen
import com.example.e_book_libruary_app.presentation.bottom_navigation_bar.BottomNavBar
import com.example.e_book_libruary_app.presentation.main.MainScreen
import com.example.e_book_libruary_app.presentation.search.SearchScreen
import com.example.e_book_libruary_app.presentation.sign_in.GoogleAuthUiClient
import com.example.e_book_libruary_app.presentation.sign_in.SignInScreen
import com.example.e_book_libruary_app.presentation.sign_in.SignInViewModel
import com.example.e_book_libruary_app.ui.theme.EBook_libruary_appTheme
import com.example.e_book_libruary_app.util.Routes
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EBook_libruary_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(navController = navController)
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.SIGN_IN_SCREEN,
                            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(200))},
                            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(200))},
                            popEnterTransition =  { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(200))},
                            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(200))},
                        ) {
                            composable(Routes.SIGN_IN_SCREEN) {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                LaunchedEffect(key1 = Unit) {
                                    if(googleAuthUiClient.getSignedInUser() != null) {
                                        navController.navigate(Routes.MAIN_SCREEN)
                                    }
                                }

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = {result ->
                                        if(result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult = googleAuthUiClient.getSignInWithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )

                                LaunchedEffect(key1 = state.isSignInSuccessful) {
                                    if(state.isSignInSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign in is successful",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.navigate(Routes.MAIN_SCREEN)
                                        viewModel.resetState()
                                    }
                                }

                                SignInScreen(
                                    state = state,
                                    onSignInClick = {
                                        lifecycleScope.launch {
                                            val signInIntentSender = googleAuthUiClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    }
                                )
                            }

                            composable(Routes.MAIN_SCREEN) {
                                MainScreen(
                                    userData = googleAuthUiClient.getSignedInUser(),
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    },
                                    googleAuthUiClient = googleAuthUiClient
                                )
                            }

                            composable(Routes.SEARCH_SCREEN) {
                                SearchScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    },
                                    onPopBackStack = {
                                        navController.popBackStack()
                                    }
                                )
                            }

                            composable(
                                route = Routes.BOOK_SCREEN + "?bookId={bookId}",
                                arguments = listOf(
                                    navArgument(name = "bookId") {
                                        type = NavType.StringType
                                        defaultValue = ""
                                    }
                                )
                            ) {
                                BookCardScreen(
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
                }
            }
        }
    }
}