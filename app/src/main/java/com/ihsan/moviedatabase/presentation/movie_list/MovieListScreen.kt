package com.ihsan.moviedatabase.presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ihsan.moviedatabase.presentation.movie_list.components.TopAppbar
import com.ihsan.moviedatabase.presentation.movie_list.components.PagerItem

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
                Box(modifier = Modifier.height(220.dp)) {
                    HorizontalPager(
                        count = movies.size,
                        state = pagerState,
                        verticalAlignment = Alignment.Top,
                    ) {
                        val movie = state.popularMovies[it]
                        PagerItem(movie = movie)
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        indicatorWidth = 5.dp,
                        modifier = Modifier.align(Alignment.BottomCenter)
                            .zIndex(10f)
                            .padding(bottom = 10.dp)
                    )
                }
            }
        }
    }
}
