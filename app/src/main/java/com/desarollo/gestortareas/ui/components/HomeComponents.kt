package com.desarollo.gestortareas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.desarollo.gestortareas.Models.Tarea
import com.desarollo.gestortareas.ui.ViewModels.TareaViewModel

@Composable
fun DiaCard() {
    Column(modifier = Modifier
        .padding(horizontal = 10.dp)
        .shadow(5.dp, shape = RoundedCornerShape(15), clip = true)
        .background(Color.White)
        .height(55.dp)
        .width(50.dp)
        .clickable {},
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("5", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Feb.",modifier = Modifier.padding(3.dp,0.dp,0.dp,0.dp))
    }
}

@Composable
fun Estadistica(name: String, stat: String) {
    Column(
        Modifier
            .padding(horizontal = 12.dp)
            .shadow(10.dp, shape = RoundedCornerShape(15), clip = true)
            .background(Color(180, 255, 255))
            .height(70.dp)
            .widthIn(max = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(name, fontSize = 20.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(vertical = 5.dp, horizontal = 8.dp))
        Text(stat, fontSize = 15.sp,modifier = Modifier.padding(horizontal = 8.dp))
    }
}

@Composable
fun NoTareaCard() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .padding(vertical = 5.dp)
            .shadow(8.dp, shape = RoundedCornerShape(15), clip = true)
            .background(Color.DarkGray)
            .heightIn(min = 90.dp)
            .width(400.dp)
            .padding(10.dp), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Parece que no hay ninguna tarea guardada", fontSize = 13.sp, color = Color.LightGray)
            Text(text = "Para añadir una tarea nueva, presiona el botón", fontSize = 13.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                Modifier
                    .shadow(2.dp, RoundedCornerShape(25), true)
                    .background(Color.Cyan)
                    .padding(5.dp)) {
                Icon(Icons.Filled.Add,contentDescription = "")
            }
        }
    }
}

@Composable
fun TareaCard(tarea: Tarea,navController: NavHostController,tareaViewModel: TareaViewModel) {
    val checked = remember { mutableStateOf(tarea.isDone) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .padding(vertical = 5.dp)
            .shadow(8.dp, shape = RoundedCornerShape(15), clip = true)
            .background(if (checked.value) Color.LightGray else Color.Cyan)
            .height(90.dp)
            .width(400.dp)
            .padding(10.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Delete, contentDescription = "Eliminar Tarea", Modifier.clickable {
                    tareaViewModel.dao.delete(tarea)
                    tareaViewModel.tareas = tareaViewModel.dao.getAll()
                    navController.navigate("Home")
                }, tint = if (checked.value) Color.Gray else Color.Black)
                Spacer(modifier = Modifier.width(5.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("Desc/${tarea.título}") },
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        val title: String
                        if (tarea.título.length>12) title = "${tarea.título.slice(IntRange(0,9))}..." else title = tarea.título
                        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = if (checked.value) Color.Gray else Color.Black)
                        val desc: String
                        if (tarea.desc.length>22) desc = "${tarea.desc.slice(IntRange(0,21))}..." else desc = tarea.desc
                        Text(desc, fontSize = 12.sp, color = if (checked.value) Color.Gray else Color.Black)
                    }

                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(tarea.fecha, fontSize = 12.sp, color = if (checked.value) Color.Gray else Color.Black)
                            Box(
                                Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(if (checked.value) Color.Gray else if (tarea.categoria == "Ninguna") Color.LightGray else Color.Yellow)) {
                                Text(tarea.categoria, Modifier.padding(7.dp,2.dp,7.dp,2.dp), fontSize = 10.sp, color = if (checked.value) Color.LightGray else Color.Black)
                            }
                        }

                        Checkbox(checked = checked.value, onCheckedChange = {
                            checked.value = it
                            tareaViewModel.onDoneChange(tarea.título)
                            if (checked.value) tareaViewModel.doneSize.value++ else tareaViewModel.doneSize.value--
                            println(tareaViewModel.doneSize)
                        })
                    }
                }
            }


        }
    }

}