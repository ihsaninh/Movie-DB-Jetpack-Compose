package com.ihsan.moviedatabase.presentation.layouts.movie_detail

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
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    var state by mutableStateOf(MovieDetailState())

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            repository.getMovieDetail(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            movie = result.data
                        )
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}