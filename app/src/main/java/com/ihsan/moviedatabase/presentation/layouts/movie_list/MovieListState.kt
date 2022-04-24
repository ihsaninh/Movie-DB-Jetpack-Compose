package com.ihsan.moviedatabase.presentation.layouts.movie_list

import com.ihsan.moviedatabase.data.remote.dto.Movie

data class MovieListState(
    val popularMovies: List<Movie>? = emptyList(),
    val topRatedMovies: List<Movie>? = emptyList(),
    val upcomingMovies: List<Movie>? = emptyList(),
    val moviesByGenre: List<Movie>? = emptyList(),

    val isLoading: Boolean = false,
    val loadingTopRated: Boolean = false,
    val loadingUpcoming: Boolean = false,

    val isRefreshing: Boolean = false,
)
