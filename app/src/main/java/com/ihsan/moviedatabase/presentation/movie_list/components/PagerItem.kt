package com.ihsan.moviedatabase.presentation.movie_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ihsan.moviedatabase.data.remote.MovieApi
import com.ihsan.moviedatabase.data.remote.dto.Movie

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerItem(
    movie: Movie,
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        tileMode = TileMode.Clamp
    )

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("${MovieApi.IMAGE_URL}${movie.backdropPath}")
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = "image",
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        )
        Text(
            text = movie.title,
            color = Color.White,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(100f)
                .padding(bottom = 30.dp, start = 10.dp)
        )
    }
}