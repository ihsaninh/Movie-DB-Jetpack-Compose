package com.ihsan.moviedatabase.domain.repository

import com.ihsan.moviedatabase.domain.model.MovieList
import com.ihsan.moviedatabase.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovie(): Flow<Resource<MovieList>>
}