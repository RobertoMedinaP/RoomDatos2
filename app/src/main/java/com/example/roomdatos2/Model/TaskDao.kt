package com.example.roomdatos2.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface TaskDao {

    //para que interactue con la base de datos debo poner la anotacion
    //y la estrategia de conflicto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    //para insertar varias tareas recibe una lista
    @Insert
    fun insertMultipleTask(list: List<Task>)

    //para actualizar
    @Update
    fun updateTask(task: Task)

    //para borrar una tarea
    @Delete
    fun deleteOneTask(task: Task)

    @Query("SELECT * FROM TASK_TABLE")
    fun getAllTask1(): List<Task>

    @Query("SELECT * FROM TASK_TABLE ORDER BY idTask ASC")
    fun getAllTask(): List<Task>

   /* @Query("SELECT * FROM TASK_TABLE WHERE idTask=idTask LIMIT 1")
    fun getTaskById(idTask: Int): Task*/
}