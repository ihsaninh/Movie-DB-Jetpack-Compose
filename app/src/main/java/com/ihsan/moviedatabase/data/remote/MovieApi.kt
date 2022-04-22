package com.ihsan.moviedatabase.data.remote

import com.ihsan.moviedatabase.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?page=1")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto


    companion object {
        const val API_KEY = "52c752b31bfe181e2fa03ee3fb20eecd"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}