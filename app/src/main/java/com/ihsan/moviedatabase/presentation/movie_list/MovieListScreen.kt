package com.ihsan.moviedatabase.presentation.movie_list

import android.util.Log
import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.pager.*
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.ihsan.moviedatabase.R
import com.ihsan.moviedatabase.data.remote.MovieApi
import com.ihsan.moviedatabase.data.remote.dto.Movie
import com.ihsan.moviedatabase.domain.model.MovieGenre
import com.ihsan.moviedatabase.domain.model.MovieList
import com.ihsan.moviedatabase.domain.model.movieGenres
import com.ihsan.moviedatabase.presentation.destinations.MovieDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ihsan.moviedatabase.presentation.movie_list.components.TopAppbar
import com.ihsan.moviedatabase.presentation.movie_list.components.PagerItem
import com.ihsan.moviedatabase.util.Constants
import com.ihsan.moviedatabase.util.Constants.IMAGE_URL
import java.util.*

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
    var tabIndex by remember { mutableStateOf(0) }

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
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .zIndex(10f)
                            .padding(bottom = 10.dp)
                    )
                }
            }
            ScrollableTabRow(selectedTabIndex = tabIndex, edgePadding = 0.dp) {
                movieGenres.forEachIndexed { index, genre ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index;
                            viewModel.getMovieByGenre(movieGenres[index].id)
                        },
                        text = {
                            Text(
                                text = genre.name.uppercase(Locale.getDefault()),
                                style = MaterialTheme.typography.button
                            )
                        }
                    )
                }
            }
            if (state.isLoading) {
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
                LazyRow {
                    state.moviesByGenre?.let { movies ->
                        items(movies.size) {
                            val movie = movies[it]
                            MovieCard(movie = movie, onClick = { navigator.navigate(MovieDetailScreenDestination) })
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 12.dp, top = 8.dp)
            .height(250.dp)
            .width(120.dp),
        elevation = 0.dp,
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${IMAGE_URL}${movie.posterPath}")
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = "image",
                modifier = Modifier
                    .height(180.dp)
                    .width(120.dp)
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Row {
                Text(
                    text = movie.voteAverage.toString(),
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.padding(start = 4.dp))
                RatingBar(
                    modifier = Modifier.padding(top = 2.dp),
                    value = (movie.voteAverage / 2).toFloat(),
                    config = RatingBarConfig().size(12.dp),
                    onValueChange = {},
                    onRatingChanged = {}
                )
            }
        }
    }
}
