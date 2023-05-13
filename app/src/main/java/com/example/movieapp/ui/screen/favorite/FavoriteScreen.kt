package com.example.movieapp.ui.screen.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.data.local.entity.MovieItemDb
import com.example.movieapp.ui.components.MaximizedMovieItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel(),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.getAllFavMovies().observeAsState().value?.let {
        if (it.isEmpty()) {
            EmptyFavorite(modifier = modifier)
        } else {
            FavoriteContent(items = it, navigateToDetail = navigateToDetail, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteContent(
    items: List<MovieItemDb>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(count = items.size, key = { items[it].id }, itemContent = {
            MaximizedMovieItem(
                imageUrl = "https://image.tmdb.org/t/p/original${items[it].imageUrl}",
                title = items[it].title,
                rating = items[it].rating.toString(),
                modifier = Modifier.clickable {
                    navigateToDetail(items[it].id.toLong())
                }
            )
        })
    }
}

@Composable
fun EmptyFavorite(
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.empty_favorite))
    }
}
