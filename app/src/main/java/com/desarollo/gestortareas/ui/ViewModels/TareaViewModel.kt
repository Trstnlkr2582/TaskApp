package com.desarollo.gestortareas.ui.ViewModels

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desarollo.gestortareas.Models.Tarea
import com.desarollo.gestortareas.Models.TareaDao
import kotlinx.coroutines.launch
import java.util.Date

class TareaViewModel(tareaDao: TareaDao): ViewModel() {
    val dao = tareaDao
    var tareas: List<Tarea> = arrayListOf()

    private val _titulo = MutableLiveData<String>()
    val titulo : LiveData<String> = _titulo

    private val _descripcion = MutableLiveData<String>()
    val descripcion : LiveData<String> = _descripcion

    private val _categoria = MutableLiveData<String>()
    val categoria : LiveData<String> = _categoria

    private val _fecha = MutableLiveData<Date>()
    val fecha : LiveData<Date> = _fecha
    var doneSize: MutableState<Int> = mutableIntStateOf(0)
    init {
        viewModelScope.launch {
            tareas = tareaDao.getAll()
            tareas.forEach {item ->
                if (item.isDone) doneSize.value++
            }
        }
    }

    fun onChange(titulo: String, descripcion: String) {
        _titulo.value = titulo
        _descripcion.value = descripcion
    }

    fun crearTarea(id: Int, title: String, limit: String, desc: String, cat:String) {
        val tarea = Tarea(id,title,limit,desc,cat,false)
        dao.insertAll(tarea)
        tareas = dao.getAll()
    }

    fun onDoneChange(title: String) {
        var tarea = dao.findByTitle(title)
        dao.changeDone(!tarea.isDone, title)
        tareas.find { it == tarea }?.isDone = !tarea.isDone
    }

    fun buttonEnable(title: String, limit: String, desc: String):Boolean {
        return title!="" && limit!="Seleccione una fecha" && desc != ""
    }

}