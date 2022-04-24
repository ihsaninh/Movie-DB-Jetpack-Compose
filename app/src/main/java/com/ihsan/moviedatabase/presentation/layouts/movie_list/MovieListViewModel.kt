package com.ihsan.moviedatabase.presentation.layouts.movie_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihsan.moviedatabase.domain.repository.MovieRepository
import com.ihsan.moviedatabase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(MovieListState())

    init {
        getPopularMovies()
        getMovieByGenre(28)
        getTopRatedMovies()
        getTopUpcomingMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            repository.getPopularMovie()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.results.let {
                                state = state.copy(
                                    popularMovies = it?.take(5)
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }
                }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            repository.getTopRatedMovie()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.results.let {
                                state = state.copy(
                                    topRatedMovies = it
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(
                                loadingTopRated = result.isLoading
                            )
                        }
                    }
                }
        }
    }

    private fun getTopUpcomingMovies() {
        viewModelScope.launch {
            repository.getUpcomingMovie()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.results.let {
                                state = state.copy(
                                    upcomingMovies = it
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(
                                loadingUpcoming = result.isLoading
                            )
                        }
                    }
                }
        }
    }

    fun getMovieByGenre(genreId: Int) {
        viewModelScope.launch {
            repository.getMovieByGenre(genreId = genreId)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.results.let {
                                state = state.copy(
                                    moviesByGenre = it
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }
                }
        }
    }
}