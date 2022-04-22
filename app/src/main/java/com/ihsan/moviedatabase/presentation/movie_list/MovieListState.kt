package com.ihsan.moviedatabase.presentation.movie_list

import com.ihsan.moviedatabase.data.remote.dto.Movie
import com.ihsan.moviedatabase.domain.model.MovieList

data class MovieListState(
    val popularMovies: List<Movie>? = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)
