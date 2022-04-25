package com.ihsan.moviedatabase.presentation.layouts.movie_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ihsan.moviedatabase.domain.model.movieGenres
import com.ihsan.moviedatabase.presentation.components.CarouselSlider
import com.ihsan.moviedatabase.presentation.components.MovieCard
import com.ihsan.moviedatabase.presentation.components.MovieListCard
import com.ihsan.moviedatabase.presentation.components.MovieListHeader
import com.ihsan.moviedatabase.presentation.layouts.destinations.MovieDetailScreenDestination
import com.ihsan.moviedatabase.presentation.layouts.movie_list.components.TopAppbar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun MovieListScreen(
    navigator: DestinationsNavigator,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val pagerState = rememberPagerState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
            )
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            TopAppbar()
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            state.popularMovies?.let { movies ->
                CarouselSlider(
                    state = movies,
                    pagerState = pagerState
                )
            }
            ScrollableTabRow(
                selectedTabIndex = viewModel.state.activeTabIndex,
                edgePadding = 0.dp,
                divider = {
                    TabRowDefaults.Divider(
                        thickness = 0.dp,
                        color = Color.Transparent
                    )
                },
            ) {
                movieGenres.forEachIndexed { index, genre ->
                    Tab(
                        selected = viewModel.state.activeTabIndex == index,
                        onClick = {
                            viewModel.state.activeTabIndex = index;
                            viewModel.getMovieByGenre(movieGenres[index].id)
                        },
                        text = {
                            Text(
                                text = genre.name.uppercase(),
                                style = MaterialTheme.typography.button,
                            )
                        }
                    )
                }
            }
            MovieListCard(
                loadingState = state.isLoading,
                height = 274.dp,
                content = {
                    LazyRow(
                        contentPadding = PaddingValues(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        state.moviesByGenre?.let { movies ->
                            items(movies.size) {
                                val movie = movies[it]
                                val movieId = movie.id.toString()
                                MovieCard(
                                    movie = movie,
                                    onClick = {
                                        navigator.navigate(
                                            MovieDetailScreenDestination(movieId = movieId)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            )
            MovieListHeader(
                title = "Top Rated Movies",
                subTitle = "See All",
                onClick = {}
            )
            MovieListCard(
                loadingState = state.loadingTopRated,
                content = {
                    LazyRow(
                        contentPadding = PaddingValues(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        state.topRatedMovies?.let { movies ->
                            items(movies.size) {
                                val movie = movies[it]
                                val movieId = movie.id.toString()
                                MovieCard(
                                    movie = movie,
                                    onClick = {
                                        navigator.navigate(
                                            MovieDetailScreenDestination(movieId = movieId)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            )
            MovieListHeader(
                title = "Upcoming Movies",
                subTitle = "See All",
                onClick = {}
            )
            MovieListCard(
                loadingState = state.loadingUpcoming,
                content = {
                    LazyRow(
                        contentPadding = PaddingValues(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        state.upcomingMovies?.let { movies ->
                            items(movies.size) {
                                val movie = movies[it]
                                val movieId = movie.id.toString()
                                MovieCard(
                                    movie = movie,
                                    onClick = {
                                        navigator.navigate(
                                            MovieDetailScreenDestination(movieId = movieId)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}