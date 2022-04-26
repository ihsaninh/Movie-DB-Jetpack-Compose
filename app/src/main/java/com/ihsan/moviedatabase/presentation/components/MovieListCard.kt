package com.ihsan.moviedatabase.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MovieListCard(
    loadingState: Boolean,
    height: Dp = 250.dp,
    content: @Composable () -> Unit,
) {
    if (loadingState) {
        Column(
            modifier = Modifier
                .height(height)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {
        content()
    }
}