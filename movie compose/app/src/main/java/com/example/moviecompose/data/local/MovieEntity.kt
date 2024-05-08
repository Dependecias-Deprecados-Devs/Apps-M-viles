package com.example.moviecompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    //@ColumnInfo(name = "codigo") --opcional
    val id: Int
)
