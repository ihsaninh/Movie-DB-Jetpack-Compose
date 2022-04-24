package com.ihsan.moviedatabase.presentation.layouts.movie_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ihsan.moviedatabase.presentation.layouts.movie_list.components.TopAppbar
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun MovieDetailScreen() {
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            TopAppbar(useSearch = false)
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "Movie detail screen")
        }
    }
}