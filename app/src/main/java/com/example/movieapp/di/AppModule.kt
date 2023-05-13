package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.local.room.MovieDao
import com.example.movieapp.data.local.room.MovieDatabase
import com.example.movieapp.data.remote.retrofit.ApiConfig
import com.example.movieapp.ui.screen.detail.DetailViewModel
import com.example.movieapp.ui.screen.favorite.FavoriteViewModel
import com.example.movieapp.ui.screen.home.HomeViewModel
import com.example.movieapp.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java, "MovieDb"
        ).build()
    }

    fun provideDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

    single { provideDatabase(get()) }
    single { provideDao(get()) }

}

val apiModule = module {
    single { ApiConfig.getApiService() }
}

val repositoryModule = module {
    single { MovieRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}