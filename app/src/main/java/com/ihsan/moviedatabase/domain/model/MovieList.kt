package com.ihsan.moviedatabase.domain.model

import com.ihsan.moviedatabase.data.remote.dto.Movie

data class MovieList(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
