package com.ihsan.moviedatabase.presentation.layouts.movie_detail

import com.ihsan.moviedatabase.data.remote.dto.Cast
import com.ihsan.moviedatabase.data.remote.dto.Movie
import com.ihsan.moviedatabase.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val similarMovies: List<Movie>? = emptyList(),
    val movieCast: List<Cast>? = emptyList(),

    val isLoading: Boolean = false,
    val isSimilarMoviesLoading: Boolean = false,
    val isMovieCastLoading: Boolean = false,
)