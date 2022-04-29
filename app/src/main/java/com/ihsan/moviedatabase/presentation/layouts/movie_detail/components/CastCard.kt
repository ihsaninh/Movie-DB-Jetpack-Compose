package com.ihsan.moviedatabase.presentation.layouts.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ihsan.moviedatabase.data.remote.dto.Cast
import com.ihsan.moviedatabase.util.Constants

@Composable
fun CastCard(
    cast: Cast,
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
            if (cast.profilePath != null) {
                AsyncImage(
                    model = "${Constants.IMAGE_URL}${cast.profilePath}",
                    contentDescription = null,
                    modifier = Modifier
                        .height(180.dp)
                        .width(120.dp)
                )
            } else {
                Surface(
                    modifier = Modifier
                        .height(180.dp)
                        .width(120.dp),
                    color = MaterialTheme.colors.primary
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.24f),
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = cast.name,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = cast.character,
                fontSize = 13.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.7f),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}