package com.example.movieapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.remote.response.PopularMoviesResponse
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private var _items: MutableStateFlow<Resource<PopularMoviesResponse>> =
        MutableStateFlow(Resource.Loading)
    val item: StateFlow<Resource<PopularMoviesResponse>> = _items

    fun getPopularMovies() {
        viewModelScope.launch {
            repository.getPopularMovies().collect {
                _items.value = it
            }
        }
    }


}