package com.ihsan.moviedatabase.data.remote

import com.ihsan.moviedatabase.data.remote.dto.MovieDetailDto
import com.ihsan.moviedatabase.data.remote.dto.MovieListDto
import com.ihsan.moviedatabase.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?page=1")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("discover/movie?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMovieByGenre(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") withGenres: String
    ): MovieListDto

    @GET("movie/top_rated?language=en-EN&page=1")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieListDto

    @GET("movie/upcoming?language=en-EN&page=1")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieListDto

    @GET("movie/{movieId}?append_to_response=images")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetailDto

}