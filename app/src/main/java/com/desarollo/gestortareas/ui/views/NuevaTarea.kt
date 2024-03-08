package com.desarollo.gestortareas.ui.views

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavHostController
import com.desarollo.gestortareas.R
import com.desarollo.gestortareas.ui.ViewModels.TareaViewModel
import com.desarollo.gestortareas.ui.components.CategoriaDropDown
import com.desarollo.gestortareas.ui.components.CrearNuevaTareaButton
import com.desarollo.gestortareas.ui.components.DescripcionTextField
import com.desarollo.gestortareas.ui.components.FechaLimite
import com.desarollo.gestortareas.ui.components.TituloTextField
import java.util.Date
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaTareaScreen(tareaViewModel: TareaViewModel, navController: NavHostController, context: Context) {
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
                        onClick = { /*TODO*/ },
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
        val titulo: String by tareaViewModel.titulo.observeAsState("")
        val descripcion: String by tareaViewModel.descripcion.observeAsState("")
        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(ScrollState(0))) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Nueva Tarea", fontSize = 25.sp)
                Spacer(Modifier.height(15.dp))
                TituloTextField(titulo) { tareaViewModel.onChange(it, descripcion)}
                Spacer(Modifier.height(15.dp))
                DescripcionTextField(descripcion) {tareaViewModel.onChange(titulo, it)}
                Spacer(Modifier.height(15.dp))
                Text("Categoría", fontSize = 25.sp)
                Spacer(Modifier.height(15.dp))
                var categoria = CategoriaDropDown()
                Spacer(Modifier.height(15.dp))
                Text("Fecha límite", fontSize = 25.sp)
                Spacer(Modifier.height(15.dp))
                var fecha = FechaLimite().split("/")
                Spacer(Modifier.height(15.dp))
                CrearNuevaTareaButton(enable = tareaViewModel.buttonEnable(titulo, fecha.toString(),descripcion)) {
                    tareaViewModel.crearTarea(0,titulo, "${fecha[0]}-${fecha[1]}-${fecha[2]}",descripcion,categoria)
                    val notification = NotificationCompat.Builder(context,"Main").also {
                        it.setContentTitle("Nueva tarea añadida!")
                        it.setContentText("La tarea '${titulo}' fue añadida correctamente!")
                        it.setSmallIcon(R.drawable.logo)
                        it.setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    }.build()
                    val notificationManager = NotificationManagerCompat.from(context)
                    navController.navigate("Home")
                    notificationManager.notify(tareaViewModel.dao.findByTitle(titulo).uid,notification)
                }
            }
        }
    }
}