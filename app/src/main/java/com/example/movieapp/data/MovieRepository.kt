package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.data.local.entity.MovieItemDb
import com.example.movieapp.data.local.room.MovieDao
import com.example.movieapp.data.remote.response.DetailMovieResponse
import com.example.movieapp.data.remote.response.PopularMoviesResponse
import com.example.movieapp.data.remote.retrofit.ApiService
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val apiKey = "a3b162c8e11218bf394576739df6b5c7"
const val language = "en-US"

class MovieRepository(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) {

    suspend fun getPopularMovies(): Flow<Resource<PopularMoviesResponse>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response = apiService.getPopularMovies(apiKey, language, 1)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                Log.e("MovieRepository", "GetPopularMoviesException: " + e.message)
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    suspend fun getDetailMovies(id: String): Flow<Resource<DetailMovieResponse>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response = apiService.getDetailMovies(id, apiKey, language)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                Log.e("MovieRepository", "GetDetailMoviesException: " + e.message)
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    suspend fun searchMovie(title: String): Flow<Resource<PopularMoviesResponse>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response = apiService.searchMovie(title, apiKey)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                Log.e("MovieRepository", "SearchMoviesException: " + e.message)
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getAllMovies() = movieDao.getAllMovie()

    fun getMovieById(id: String) = movieDao.getMovieById(id)

    suspend fun addMovie(movieItem: MovieItemDb) = movieDao.insertMovie(movieItem)

    suspend fun deleteMovie(movieItem: MovieItemDb) = movieDao.deleteMovie(movieItem)

}