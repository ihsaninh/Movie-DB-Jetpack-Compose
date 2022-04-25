package com.ihsan.moviedatabase.presentation.layouts.movie_detail

import com.ihsan.moviedatabase.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false
)