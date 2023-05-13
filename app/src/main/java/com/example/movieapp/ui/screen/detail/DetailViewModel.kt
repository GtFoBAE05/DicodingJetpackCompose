package com.example.movieapp.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.local.entity.MovieItemDb
import com.example.movieapp.data.remote.response.DetailMovieResponse
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private var _items: MutableStateFlow<Resource<DetailMovieResponse>> =
        MutableStateFlow(Resource.Loading)
    val item: StateFlow<Resource<DetailMovieResponse>> = _items

    fun getDetailMovie(id: String) {
        viewModelScope.launch {
            repository.getDetailMovies(id).collect {
                _items.value = it
            }
        }
    }

    fun getMovieById(id: String): LiveData<Boolean> = repository.getMovieById(id)

    fun addMovie(movieItem: MovieItemDb) {
        viewModelScope.launch {
            repository.addMovie(movieItem)
        }
    }

    fun deleteMovie(movieItem: MovieItemDb) {
        viewModelScope.launch {
            repository.deleteMovie(movieItem)
        }
    }
}