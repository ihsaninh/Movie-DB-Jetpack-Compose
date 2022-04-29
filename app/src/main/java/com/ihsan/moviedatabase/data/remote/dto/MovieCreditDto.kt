package com.ihsan.moviedatabase.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MovieCreditDto(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)