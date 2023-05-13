package com.example.movieapp.ui.screen.search

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import com.example.movieapp.ui.components.ListMovieItems
import com.example.movieapp.ui.components.LoadingComponent
import com.example.movieapp.ui.components.SearchBar
import com.example.movieapp.utils.Resource
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel(),
    navigateToDetail: (Long) -> Unit
) {
    val context = LocalContext.current

    val query = viewModel.query

    Column(modifier = modifier) {
        SearchBar(query = query.value, onQueryChange = viewModel::searchMovie, modifier = modifier.testTag("searchbar"))
        if (query.value.isNotEmpty()) {
            viewModel.item.collectAsState().value.let {
                when (it) {
                    is Resource.Loading -> {
                        LoadingComponent()
                    }

                    is Resource.Error -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        ListMovieItems(
                            movieItem = it.data.results,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail
                        )
                    }
                }
            }
        }

    }


}


