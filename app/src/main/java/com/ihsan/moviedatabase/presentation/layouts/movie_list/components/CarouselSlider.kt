package com.ihsan.moviedatabase.presentation.layouts.movie_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.ihsan.moviedatabase.data.remote.dto.Movie

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselSlider(
    state: List<Movie>,
    pagerState: PagerState
) {
    Box(modifier = Modifier.height(220.dp)) {
        HorizontalPager(
            count = state.size,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) {
            val movie = state[it]
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