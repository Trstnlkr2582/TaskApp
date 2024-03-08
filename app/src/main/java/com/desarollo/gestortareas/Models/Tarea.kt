package com.desarollo.gestortareas.Models

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import java.util.Date

@Entity
data class Tarea(
    @PrimaryKey(autoGenerate = true) val uid : Int,
    @ColumnInfo("Título") val título: String,
    @ColumnInfo("Fecha_límite") val fecha: String,
    @ColumnInfo("Descripción") val desc: String,
    @ColumnInfo("Categoría") val categoria: String,
    @ColumnInfo("Hecha?") var isDone: Boolean
) {
}

@Database(entities = [Tarea::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tareaDao(): TareaDao
}