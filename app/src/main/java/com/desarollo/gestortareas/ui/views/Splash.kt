package com.desarollo.gestortareas.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.desarollo.gestortareas.ui.components.Splash
import kotlinx.coroutines.delay

@Composable
fun SplashView(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(500)
        navController.popBackStack()
        navController.navigate("Login")
    }

    Splash()
}