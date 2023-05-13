package com.example.movieapp.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.data.local.entity.MovieItemDb
import com.example.movieapp.data.remote.response.Genre
import com.example.movieapp.ui.components.FloatingActionButtonComponent
import com.example.movieapp.ui.components.LoadingComponent
import com.example.movieapp.utils.Resource
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    movieId: Long,
    modifier: Modifier = Modifier.testTag("detail"),
    viewModel: DetailViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    viewModel.item.collectAsState().value.let {
        when (it) {
            is Resource.Loading -> {
                LoadingComponent()
                viewModel.getDetailMovie(movieId.toString())
            }

            is Resource.Error -> {
                Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
            }

            is Resource.Success -> {
                val data = it.data

                val movie = MovieItemDb(
                    data.id.toString(),
                    data.posterPath,
                    data.title,
                    data.voteAverage
                )

                val isFavorite =
                    viewModel.getMovieById(data.id.toString()).observeAsState().value ?: false


                DetailContent(
                    isFavorite = isFavorite,
                    imageUrl = data.backdropPath,
                    title = data.title,
                    rating = data.voteAverage.toString(),
                    releaseDate = data.releaseDate,
                    genres = data.genres,
                    overview = data.overview,
                    onClick = {
                        if (isFavorite) {
                            viewModel.deleteMovie(movie)
                            Toast.makeText(context, R.string.success_delete_favorite, Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            viewModel.addMovie(movie)
                            Toast.makeText(context, R.string.success_add_favorite, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    isFavorite: Boolean,
    imageUrl: String,
    title: String,
    rating: String,
    releaseDate: String,
    genres: List<Genre>,
    overview: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF263238))

    ) {

        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            val genre = getGenre(genres)
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original$imageUrl",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .testTag("Image")
                    .fillMaxWidth()
                    .size(400.dp)
            )
            Text(
                text = title,
                modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp).testTag("Title"),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = "Rating : $rating",
                modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp).testTag("Rating"),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0XFFBDBDBD)
            )
            Text(
                text = "$releaseDate | $genre",
                modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp).testTag("Release|Genre"),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0XFFBDBDBD)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.summary),
                modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp).testTag("Summary"),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
            Text(
                text = overview,
                modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp).testTag("Overview"),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0XFFBDBDBD)
            )
        }
        FloatingActionButtonComponent(
            onClick = onClick,
            isFavorite = isFavorite,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd)
                .testTag("favoriteBtn")
        )
    }


}

fun getGenre(genre: List<Genre>): String {
    val genreList = mutableListOf<String>()
    genre.forEach {
        genreList.add(it.name)
    }
    return genreList.joinToString(",")

}

