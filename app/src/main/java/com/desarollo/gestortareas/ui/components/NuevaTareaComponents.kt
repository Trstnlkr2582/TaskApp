package com.desarollo.gestortareas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TituloTextField(titulo: String, function: (String) -> Unit) {
    TextField(value = titulo, maxLines = 1, modifier = Modifier.widthIn(280.dp,280.dp), label = { Text("Título") }, onValueChange = {
        function(it)
    })
}

@Composable
fun DescripcionTextField(desc: String, function: (String) -> Unit) {
    TextField(modifier = Modifier.height(125.dp).widthIn(280.dp,280.dp), maxLines = 4,value = desc, label = { Text("Descripción") }, onValueChange = {
        function(it)
    })
}
@Preview
@Composable
fun CategoriaDropDown(): String {
    var expanded by remember { mutableStateOf(false) }
    val categorias = listOf<String>("Ninguna","Importante")
    var selectedIndex by remember { mutableStateOf(0) }
    val disabledValue = ""
    Box(modifier = Modifier
        .clip(RoundedCornerShape(15.dp))
        .background(Color(220, 227, 233))
        .height(50.dp)
        .width(250.dp)
        .padding(20.dp, 5.dp, 5.dp, 5.dp)
        .clickable { expanded = true },
        contentAlignment = Alignment.Center) {
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
            Icon(Icons.Filled.ArrowDropDown,contentDescription = "")
            Text(text = "    "+categorias[selectedIndex],Modifier.fillMaxWidth(), fontSize = 18.sp)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false},
            modifier = Modifier
                .height(100.dp)
                .width(200.dp)) {
            categorias.forEachIndexed { index, s ->
                DropdownMenuItem(text = {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                    })

            }
        }
    }
    return categorias[selectedIndex]
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaLimite(): String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    val state = rememberDatePickerState()
    var openDialog = remember { mutableStateOf(false) }
    var fecha by remember { mutableStateOf("Seleccione una fecha") }

    Box(modifier = Modifier
        .clip(RoundedCornerShape(15.dp))
        .background(Color(220, 227, 233))
        .height(50.dp)
        .width(250.dp)
        .padding(5.dp)
        .clickable { openDialog.value = true },
        contentAlignment = Alignment.Center) {
        Text(fecha)
    }

    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        if (state.selectedDateMillis!=null) {
                            fecha = format.format(state.selectedDateMillis)
                        }
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("CANCEL")
                }
            }
        ) {
            DatePicker(
                state = state
            )
        }
    }
    return fecha
}

@Composable
fun CrearNuevaTareaButton (enable: Boolean, function: () -> Unit) {
    Button(onClick = {function()}, enabled = enable, modifier = Modifier.width(280.dp)) {
        Text("Crear nueva tarea")
    }
}