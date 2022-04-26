package com.ihsan.moviedatabase.data.mapper

import com.ihsan.moviedatabase.data.remote.dto.MovieDetailDto
import com.ihsan.moviedatabase.data.remote.dto.MovieListDto
import com.ihsan.moviedatabase.domain.model.MovieDetail
import com.ihsan.moviedatabase.domain.model.MovieList

fun MovieListDto.toMovieList(): MovieList {
    return MovieList(
        page = page,
        results = results,
        totalPages = totalPages,
        totalResults = totalResults
    )
}

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genres,
        homepage = homepage,
        id = id,
        images = images,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}