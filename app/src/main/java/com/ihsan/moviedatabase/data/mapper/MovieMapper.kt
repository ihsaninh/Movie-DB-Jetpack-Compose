package com.ihsan.moviedatabase.data.mapper

import com.ihsan.moviedatabase.data.remote.dto.MovieListDto
import com.ihsan.moviedatabase.domain.model.MovieList

fun MovieListDto.toMovieList(): MovieList {
    return MovieList(
        page = page,
        results = results,
        totalPages = totalPages,
        totalResults = totalResults
    )
}