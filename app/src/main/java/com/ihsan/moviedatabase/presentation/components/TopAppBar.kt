package com.ihsan.moviedatabase.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopAppbar(
    useSearch: Boolean = true
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        title = {
            Text(text = "Movie Database")
        },
        actions = {
            if (useSearch) IconButton(onClick = { }) {
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