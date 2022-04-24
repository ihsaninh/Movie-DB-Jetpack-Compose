package com.ihsan.moviedatabase.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.ihsan.moviedatabase.data.remote.dto.Movie
import com.ihsan.moviedatabase.util.Constants

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(250.dp)
            .width(120.dp)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }),
        elevation = 0.dp,
        color = Color.Transparent
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${Constants.IMAGE_URL}${movie.posterPath}")
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
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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