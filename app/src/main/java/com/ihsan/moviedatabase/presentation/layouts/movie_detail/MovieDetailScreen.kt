package com.ihsan.moviedatabase.presentation.layouts.movie_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ihsan.moviedatabase.R
import com.ihsan.moviedatabase.domain.model.MovieDetail
import com.ihsan.moviedatabase.presentation.components.TopAppbar
import com.ihsan.moviedatabase.presentation.layouts.movie_detail.components.BackdropSlider
import com.ihsan.moviedatabase.util.Constants
import com.ihsan.moviedatabase.util.Helper
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
@Destination
fun MovieDetailScreen(
    movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel(),
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
                MovieDetailTitleInfo(movie = movie)
                Divider(
                    color = Color.White.copy(alpha = 0.12f)
                )
                MovieDetailDescriptionInfo(movie = movie)
                Divider(
                    color = Color.White.copy(alpha = 0.12f)
                )
                MovieDetailInfoStatus(movie = movie)
                Divider(
                    color = Color.White.copy(alpha = 0.12f)
                )
            }
        }
    }
}

@Composable
fun MovieDetailTitleInfo(
    movie: MovieDetail
) {
    var expanded by remember { mutableStateOf(false) }
    val menuItems = listOf("Share", "Visit Website")
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
            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            offset = DpOffset((-40).dp, (-40).dp),
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0XFF2D2D33)),
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(
                    onClick = { expanded = false }) {
                    Text(
                        color = Color.White,
                        text = item,
                    )
                }
            }
        }
        Text(
            buildAnnotatedString {
                append(Helper.getYear(movie.releaseDate))
                append(" • ")
                append(Helper.convertToHoursMinutes(movie.runtime))
            },
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.70f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun MovieDetailDescriptionInfo(
    movie: MovieDetail
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = "${Constants.IMAGE_URL}${movie.posterPath}",
                contentScale = ContentScale.FillWidth,
                contentDescription = "image",
                modifier = Modifier
                    .height(180.dp)
                    .width(120.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(start = 8.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                movie.genres.let { genres ->
                    items(genres.size) {
                        val genre = genres[it]
                        OutlinedButton(
                            onClick = {},
                            border = BorderStroke(
                                width = 1.dp,
                                color = Color.White.copy(alpha = 0.12f)
                            ),
                            shape = RoundedCornerShape(5),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color.White,
                            )
                        ) {
                            Text(genre.name)
                        }
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = movie.overview,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                maxLines = 5,
                lineHeight = 20.sp,
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.70f),
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun MovieDetailInfoStatus(
    movie: MovieDetail
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0XFFFFBF00),
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    ) {
                        append(movie.voteAverage.toString())
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 12.sp
                        )
                    ) {
                        append("/10")
                    }
                },
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.70f),
                modifier = Modifier.padding(top = 4.dp),
            )
            Text(
                text = "Rating",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.AttachMoney,
                contentDescription = null,
                tint = Color.Green,
            )
            Text(
                text = "USD ${Helper.formatCompactCurrency(movie.revenue.toFloat())}",
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
            Text(
                text = "Revenue",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.Movie,
                contentDescription = null,
                tint = Color.Red,
            )
            Text(
                text = movie.status,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
            Text(
                text = "Status",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}