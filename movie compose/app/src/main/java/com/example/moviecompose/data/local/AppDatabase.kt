package com.example.moviecompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)   // Lo unico que cambiaria seria MovieEntity
abstract class AppDatabase : RoomDatabase() {    // una clase abstracta es como una clase + interfaz
    abstract fun getMovieDao(): MovieDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, "db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AppDatabase
        }
    }
}