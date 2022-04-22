package com.ihsan.moviedatabase.presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.*
import com.ihsan.moviedatabase.data.remote.MovieApi
import com.ihsan.moviedatabase.data.remote.dto.Movie
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPagerApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun MovieListScreen(
    navigator: DestinationsNavigator,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            TopAppbar()
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val pagerState = rememberPagerState()
            state.popularMovies?.let { movies ->
                Box() {
                    HorizontalPager(
                        count = movies.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        val movie = state.popularMovies[it]
                        PagerItem(
                            movie = movie,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .aspectRatio(1f)
                        )
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppbar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Text(text = "Movie Databases")
        },
        actions = {
            Box(
                Modifier
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        Icons.Filled.Search,
                        tint = Color.White,
                        contentDescription = "Search"
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("${MovieApi.IMAGE_URL}${movie.backdropPath}")
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = "image",
            modifier = Modifier.matchParentSize()
        )
    }
}
