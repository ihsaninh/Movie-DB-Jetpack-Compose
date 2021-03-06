package com.ihsan.moviedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ihsan.moviedatabase.presentation.layouts.NavGraphs
import com.ihsan.moviedatabase.ui.theme.MovieDatabaseTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostEngine = rememberAnimatedNavHostEngine(
                rootDefaultAnimations = RootNavGraphDefaultAnimations(
                    enterTransition = { slideInHorizontally(initialOffsetX = { 500 }) },
                    exitTransition = { slideOutHorizontally(targetOffsetX = { -500 }) },
                    popEnterTransition = { slideInHorizontally(initialOffsetX = { -500 }) },
                    popExitTransition = { slideOutHorizontally(targetOffsetX = { 500 }) }
                )
            )

            val systemUiController = rememberSystemUiController()
            val statusBarColor = Color(0xFF1D1D27).copy(alpha = 0.99f)
            SideEffect {
                systemUiController.setSystemBarsColor(statusBarColor, darkIcons = false)
            }

            MovieDatabaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root, engine = navHostEngine)
                }
            }
        }
    }
}
