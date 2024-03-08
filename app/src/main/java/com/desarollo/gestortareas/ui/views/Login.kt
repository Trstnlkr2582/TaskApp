package com.desarollo.gestortareas.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.desarollo.gestortareas.R
import com.desarollo.gestortareas.ui.ViewModels.LogInViewModel
import com.desarollo.gestortareas.ui.components.ButtonLogin
import com.desarollo.gestortareas.ui.components.TextFieldUser
import com.desarollo.gestortareas.ui.components.TextFieldPassword

@Composable
fun LoginScreen(logInViewModel: LogInViewModel, navController: NavHostController) {
    val user : String by logInViewModel.user.observeAsState("")
    val password : String by logInViewModel.password.observeAsState("")
    Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(R.drawable.fondo1),
            "Fondo de inicio",
            Modifier
                .height(262.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text("TaskApp", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Cyan)
        Text("¡Organizate mejor!", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldUser(user) { logInViewModel.onChange(it, password) }
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldPassword(password) { logInViewModel.onChange(user, it) }
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            ButtonLogin(
                logInViewModel.buttonEnable(
                    user,
                    password
                )
            ) { navController.navigate("Home") }
        }
    }
}

