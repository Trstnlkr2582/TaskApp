package com.desarollo.gestortareas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.desarollo.gestortareas.R

@Composable
fun TextFieldUser(user: String, function: (String) -> Unit) {
    TextField(value = user, maxLines = 1, label = { Text("Usuario")}, onValueChange = {
        function(it)
    })
}

@Composable
fun TextFieldPassword(password: String, function: (String) -> Unit) {
    TextField(value = password, maxLines = 1, label = { Text("Contraseña")},
        onValueChange = {function(it)}, visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ButtonLogin(enable: Boolean, function: () -> Unit) {
    Button(onClick = {function()}, enabled = enable, modifier = Modifier.width(280.dp)) {
        Text("Entrar")
    }
}

@Composable
fun Splash() {
    Column(
        Modifier.fillMaxSize().background(Color(180,255,255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(id = R.drawable.logo),
            contentDescription = "Logo", Modifier.height(150.dp).width(250.dp))
    }
}