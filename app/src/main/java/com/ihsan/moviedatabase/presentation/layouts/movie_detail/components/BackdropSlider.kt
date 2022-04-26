package com.ihsan.moviedatabase.presentation.layouts.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.ihsan.moviedatabase.data.remote.dto.Backdrop
import com.ihsan.moviedatabase.util.Constants

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BackdropSlider(
    state: List<Backdrop>,
    pagerState: PagerState
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            MaterialTheme.colors.primary
        ),
        tileMode = TileMode.Clamp
    )

    Box(modifier = Modifier.height(220.dp)) {
        HorizontalPager(
            count = state.size,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) {
            val backdrop = state[it]

            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${Constants.IMAGE_URL}${backdrop.filePath}")
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
            }
        }
    }
}