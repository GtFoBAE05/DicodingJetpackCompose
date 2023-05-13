package com.example.movieapp.ui.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.local.entity.MovieItemDb

class FavoriteViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getAllFavMovies(): LiveData<List<MovieItemDb>> = repository.getAllMovies()

}