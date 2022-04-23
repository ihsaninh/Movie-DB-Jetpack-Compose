package com.ihsan.moviedatabase.presentation.movie_list.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TopAppbar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Text(text = "Movie Database")
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}