package com.ihsan.moviedatabase.presentation.layouts.movie_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ihsan.moviedatabase.R
import com.ihsan.moviedatabase.presentation.components.TopAppbar
import com.ihsan.moviedatabase.presentation.layouts.movie_detail.components.BackdropSlider
import com.ihsan.moviedatabase.util.Helper
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
@Destination
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: String
) {
    val movie = viewModel.state.movie
    val pagerState = rememberPagerState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getMovieDetail(movieId)
        while (true) {
            yield()
            delay(5000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
            )
        }
    }
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            TopAppbar(useSearch = false)
        }
    ) {
        if (movie != null) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                movie.images.backdrops.let { movies ->
                    BackdropSlider(
                        state = movies,
                        pagerState = pagerState
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = movie.title,
                            fontFamily = FontFamily(Font(R.font.roboto_light)),
                            fontSize = 28.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light
                        )
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                    Text(
                        buildAnnotatedString {
                            append(Helper.getYear(movie.releaseDate))
                            append(" • ")
                            append(Helper.convertToHoursMinutes(movie.runtime))
                        },
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.70f),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Divider(color = Color.White.copy(alpha = 0.12f))
            }
        }
    }
}