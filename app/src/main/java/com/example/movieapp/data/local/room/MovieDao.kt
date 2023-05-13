package com.example.movieapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.local.entity.MovieItemDb

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): LiveData<List<MovieItemDb>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id)")
    fun getMovieById(id: String): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieItem: MovieItemDb)

    @Delete
    suspend fun deleteMovie(movieItem: MovieItemDb)


}