package com.ihsan.moviedatabase.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("backdrops")
    val backdrops: List<Backdrop>?,
    @SerializedName("logos")
    val logos: List<Logo>?,
    @SerializedName("posters")
    val posters: List<Poster>?
)