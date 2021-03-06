package com.ihsan.moviedatabase.domain.repository

import com.ihsan.moviedatabase.domain.model.MovieCredit
import com.ihsan.moviedatabase.domain.model.MovieDetail
import com.ihsan.moviedatabase.domain.model.MovieList
import com.ihsan.moviedatabase.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovie(): Flow<Resource<MovieList>>
    suspend fun getMovieByGenre(genreId: Int): Flow<Resource<MovieList>>
    suspend fun getTopRatedMovie(): Flow<Resource<MovieList>>
    suspend fun getUpcomingMovie(): Flow<Resource<MovieList>>
    suspend fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>>
    suspend fun getSimilarMovie(movieId: String): Flow<Resource<MovieList>>
    suspend fun getMovieCredits(movieId: String): Flow<Resource<MovieCredit>>
}