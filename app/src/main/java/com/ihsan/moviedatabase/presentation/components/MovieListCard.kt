package com.ihsan.moviedatabase.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ihsan.moviedatabase.data.remote.dto.Movie

@Composable
fun MovieListCard(
    movieState: List<Movie>?,
    loadingState: Boolean,
    onClick: () -> Unit,
) {
    if (loadingState) {
        Column(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {
        LazyRow(
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            movieState?.let { movies ->
                items(movies.size) {
                    val movie = movies[it]
                    MovieCard(
                        movie = movie,
                        onClick = onClick
                    )
                }
            }
        }
    }
}