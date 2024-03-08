package com.desarollo.gestortareas.ui.views

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.desarollo.gestortareas.ui.ViewModels.TareaViewModel
import com.desarollo.gestortareas.ui.components.DiaCard
import com.desarollo.gestortareas.ui.components.Estadistica
import com.desarollo.gestortareas.ui.components.NoTareaCard
import com.desarollo.gestortareas.ui.components.TareaCard
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, tareaViewModel: TareaViewModel) {
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
                    IconButton(onClick = {navController.navigate("Login")}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Cerrar Sesión"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {navController.navigate("Usuario")}) {
                        Icon(Icons.Filled.Person,
                            contentDescription = "Usuario")
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
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                Modifier
                    .padding(20.dp)
                    .horizontalScroll(ScrollState(0))) {
                DiaCard()
                DiaCard()
                DiaCard()
                DiaCard()
                DiaCard()
                DiaCard()
                DiaCard()
            }
            Row(
                Modifier
                    .padding(horizontal = 20.dp)
                    .horizontalScroll(ScrollState(0))) {
                var size = if (tareaViewModel.tareas.isEmpty()) 1 else tareaViewModel.tareas.size
                var percent: Float = tareaViewModel.doneSize.value.toFloat()*100/size
                percent = percent.roundToInt().toFloat()
                Estadistica("Total","${tareaViewModel.tareas.size} Tareas")
                Estadistica("Hechas", "${tareaViewModel.doneSize.value.toString()} Tareas")
                Estadistica("Porcentaje","${percent}% Hecho")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                Modifier
                    .padding(horizontal = 30.dp, vertical = 5.dp)) {
                Text("Tareas del día:", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                if (tareaViewModel.tareas.isEmpty()) {
                    NoTareaCard()
                }
                else {
                    Column(
                        Modifier
                            .verticalScroll(ScrollState(0))) {
                        tareaViewModel.tareas.forEach { item->
                            TareaCard(item,navController,tareaViewModel)

                        }
                    }
                }
            }
        }
    }
}