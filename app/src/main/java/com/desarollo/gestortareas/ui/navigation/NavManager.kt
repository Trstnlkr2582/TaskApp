package com.desarollo.gestortareas.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desarollo.gestortareas.Models.TareaDao
import com.desarollo.gestortareas.ui.ViewModels.LogInViewModel
import com.desarollo.gestortareas.ui.ViewModels.TareaViewModel
import com.desarollo.gestortareas.ui.views.DescView
import com.desarollo.gestortareas.ui.views.HomeScreen
import com.desarollo.gestortareas.ui.views.LoginScreen
import com.desarollo.gestortareas.ui.views.NuevaTareaScreen
import com.desarollo.gestortareas.ui.views.SplashView
import com.desarollo.gestortareas.ui.views.UsuarioScreen

@Composable
fun NavManagement(Dao: TareaDao, context: Context) {
    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Splash"){
        composable("Login") {
            LoginScreen(LogInViewModel(),navController)
        }
        composable("Home") {
            HomeScreen(navController,TareaViewModel(Dao))
        }
        composable("Splash") {
            SplashView(navController)
        }
        composable("Usuario") {
            UsuarioScreen(navController)
        }
        composable("Nueva Tarea") {
            NuevaTareaScreen(TareaViewModel(Dao), navController, context)
        }
        composable("Desc/{title}") {backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            DescView(title ?: "", navController, TareaViewModel(Dao))
        }
    }
}