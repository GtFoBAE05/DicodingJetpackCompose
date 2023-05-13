package com.example.movieapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.movieapp.data.remote.response.PopularMoviesResult

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridMovieItems(
    movieItem: List<PopularMoviesResult>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize().testTag("GridMovieItems")
    ) {
        items(count = movieItem.size, key = { movieItem[it].id }, itemContent = {
            MaximizedMovieItem(
                imageUrl = "https://image.tmdb.org/t/p/original${movieItem[it].posterPath}",
                title = movieItem[it].title,
                rating = movieItem[it].voteAverage.toString(),
                modifier = Modifier.clickable {
                    navigateToDetail(movieItem[it].id.toLong())
                }
            )
        })
    }
}

@Composable
fun ListMovieItems(
    movieItem: List<PopularMoviesResult>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth().testTag("ListMovieItems")
    ) {
        items(count = movieItem.size, key = { movieItem[it].id }, itemContent = {
            MinimizedMovieItem(
                imageUrl = "https://image.tmdb.org/t/p/original${movieItem[it].posterPath}",
                title = movieItem[it].title,
                modifier = Modifier.clickable {
                    navigateToDetail(movieItem[it].id.toLong())
                }
            )
        })

    }
}



