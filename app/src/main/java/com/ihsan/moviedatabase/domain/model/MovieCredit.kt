package com.ihsan.moviedatabase.domain.model

import com.ihsan.moviedatabase.data.remote.dto.Cast
import com.ihsan.moviedatabase.data.remote.dto.Crew

data class MovieCredit(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)