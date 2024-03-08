package com.desarollo.gestortareas.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.desarollo.gestortareas.R
import com.desarollo.gestortareas.ui.components.Estadistica

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(180,255,255),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "TaskApp",
                        fontSize = 25.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate("Home")}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Person,
                            contentDescription = "")
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu,
                            contentDescription = "")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {navController.navigate("Nueva Tarea")},
                        containerColor = Color.Cyan,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add,
                            contentDescription = "Añadir Tarea")
                    }
                },
                containerColor = Color(180,255,255)
            )
        }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(
                Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(id = R.drawable.icono), contentDescription = "", Modifier
                    .shadow(25.dp, shape = CircleShape, clip = true)
                    .clip(RoundedCornerShape(50)))
                Spacer(modifier = Modifier.height(10.dp))
                Text("Carlos B.", fontSize = 50.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    Modifier
                        .width(350.dp)
                        .heightIn(100.dp, 300.dp)
                        .clip(RoundedCornerShape(15))
                        .background(Color(180, 255, 255)),
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Row {
                        Text("Correo:", Modifier.padding(10.dp,0.dp,0.dp,0.dp), fontWeight = FontWeight.Bold)
                        Text(" carlitosdavid.bello@gmail.com", Modifier.padding(0.dp,0.dp,10.dp,0.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text("Número de teléfono:", Modifier.padding(10.dp,0.dp,0.dp,0.dp), fontWeight = FontWeight.Bold)
                        Text(" +57 (312) 459 6406", Modifier.padding(0.dp,0.dp,10.dp,0.dp))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.padding(horizontal = 20.dp).horizontalScroll(ScrollState(0))) {
                    Estadistica(name = "Estadistica", stat = "20%")
                    Estadistica(name = "Estadistica", stat = "20%")
                    Estadistica(name = "Estadistica", stat = "20%")
                }

            }
        }
    }
}