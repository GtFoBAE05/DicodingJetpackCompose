package com.example.movieapp.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.remote.response.PopularMoviesResponse
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: MovieRepository) : ViewModel() {
    private var _items: MutableStateFlow<Resource<PopularMoviesResponse>> =
        MutableStateFlow(Resource.Loading)
    val item: StateFlow<Resource<PopularMoviesResponse>> = _items

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchMovie(title: String) {
        _query.value = title
        viewModelScope.launch {
            repository.searchMovie(title).collect {
                _items.value = it
            }
        }
    }
}