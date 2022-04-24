package com.ihsan.moviedatabase.presentation.layouts.movie_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    var tabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.scrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
            )
        }
    }

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
                .verticalScroll(scrollState)
        ) {
            state.popularMovies?.let { movies ->
                CarouselSlider(
                    state = movies,
                    pagerState = pagerState
                )
            }
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                edgePadding = 0.dp,
                divider = {
                    TabRowDefaults.Divider(
                        thickness = 0.dp,
                        color = Color.Transparent
                    )
                }
            ) {
                movieGenres.forEachIndexed { index, genre ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index;
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
                movieState = state.moviesByGenre,
                loadingState = state.isLoading,
                onClick = {
                    navigator.navigate(
                        MovieDetailScreenDestination
                    )
                }
            )
            MovieListHeader(
                title = "Top Rated Movies",
                subTitle = "See All",
                onClick = {}
            )
            MovieListCard(
                movieState = state.topRatedMovies,
                loadingState = state.loadingTopRated,
                onClick = {
                    navigator.navigate(
                        MovieDetailScreenDestination
                    )
                }
            )
            MovieListHeader(
                title = "Upcoming Movies",
                subTitle = "See All",
                onClick = {}
            )
            MovieListCard(
                movieState = state.upcomingMovies,
                loadingState = state.loadingUpcoming,
                onClick = {
                    navigator.navigate(
                        MovieDetailScreenDestination
                    )
                }
            )
        }
    }
}
