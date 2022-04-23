package com.ihsan.moviedatabase.data.repository

import com.ihsan.moviedatabase.data.mapper.toMovieList
import com.ihsan.moviedatabase.data.remote.MovieApi
import com.ihsan.moviedatabase.domain.model.MovieList
import com.ihsan.moviedatabase.domain.repository.MovieRepository
import com.ihsan.moviedatabase.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getPopularMovie(): Flow<Resource<MovieList>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val popularMovies = api.getPopularMovie()
                emit(Resource.Success(
                    data = popularMovies.toMovieList()
                ))
                emit(Resource.Loading(false))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }

    override suspend fun getMovieByGenre(genreId: Int): Flow<Resource<MovieList>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val moviesByGenre = api.getMovieByGenre(withGenres = genreId.toString())
                emit(Resource.Success(
                    data = moviesByGenre.toMovieList()
                ))
                emit(Resource.Loading(false))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }
}