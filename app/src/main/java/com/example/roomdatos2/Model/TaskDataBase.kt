package com.example.roomdatos2.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//anotacion database, se indica donde esta la entidad, la entidad y si se exporta o no el esquema
@Database (entities = [Task::class], version = 1, exportSchema = false)

//clase abstracta e indicamos que es Roomdatabase
abstract class TaskDataBase: RoomDatabase() {

    //funcion abstracta para llamar al dao

    abstract fun getTaskDao(): TaskDao

    //construccion de base de datos
    companion object{

        @Volatile
        private var INSTANCE: TaskDataBase?=null

        fun getDatabase(context: Context):TaskDataBase{

            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    "task_Database"
                ).build()

                INSTANCE=instance
                return instance

            }
        }


    }
}

/*
version antigua
fun getDatabase(context: Context): TaskDataBase?{
           INSTANCE?: synchronized(this){
               INSTANCE= Room.databaseBuilder(
                   context.applicationContext,
                   TaskDataBase::class.java,
                   DATABASE_NAME
               ).build()

           }
       }*/