package com.example.movieapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.local.entity.MovieItemDb


@Database(
    entities = [MovieItemDb::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}