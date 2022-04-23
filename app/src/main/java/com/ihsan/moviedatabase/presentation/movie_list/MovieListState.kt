package com.ihsan.moviedatabase.presentation.movie_list

import com.ihsan.moviedatabase.data.remote.dto.Movie

data class MovieListState(
    val popularMovies: List<Movie>? = emptyList(),
    val moviesByGenre: List<Movie>? = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)
