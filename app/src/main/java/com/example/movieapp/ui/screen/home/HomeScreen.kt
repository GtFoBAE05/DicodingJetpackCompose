package com.example.movieapp.ui.screen.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R
import com.example.movieapp.ui.components.GridMovieItems
import com.example.movieapp.ui.components.LoadingComponent
import com.example.movieapp.utils.Resource
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToDetail: (Long) -> Unit
) {
    val context = LocalContext.current

    viewModel.item.collectAsState().value.let {
        when (it) {
            is Resource.Loading -> {
                LoadingComponent()
                viewModel.getPopularMovies()
            }

            is Resource.Error -> {
                Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
            }

            is Resource.Success -> {
                GridMovieItems(
                    movieItem = it.data.results,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}



