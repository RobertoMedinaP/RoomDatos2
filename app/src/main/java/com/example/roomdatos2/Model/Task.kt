package com.example.roomdatos2.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


//representacion de la base de datos
@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    @NonNull val idTask: Int=0,
    val task: String,
    val descripcion: String,
    val date: String


        )