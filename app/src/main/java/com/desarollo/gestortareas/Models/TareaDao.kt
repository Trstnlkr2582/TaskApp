package com.desarollo.gestortareas.Models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TareaDao {
    @Query("Select * from tarea")
    fun getAll(): List<Tarea>

    @Query("SELECT * FROM tarea WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Tarea>

    @Query("SELECT * FROM tarea WHERE Título LIKE :titulo LIMIT 1")
    fun findByTitle (titulo: String): Tarea

    @Query("Update tarea set `Hecha?` = :done where Título LIKE :title")
    fun changeDone (done: Boolean, title: String)

    @Insert
    fun insertAll(vararg tareas: Tarea)

    @Delete
    fun delete(tarea: Tarea)
}