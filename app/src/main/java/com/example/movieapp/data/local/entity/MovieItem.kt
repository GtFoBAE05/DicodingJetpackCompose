package com.example.movieapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieItemDb(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("imgUrl")
    val imageUrl: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("rating")
    val rating: Double
)