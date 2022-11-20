package com.example.binarwatchflix.data.network.api.response.movie

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @field:SerializedName("results")
    val results: List<MovieList>,
)

class MovieList(
    @field:SerializedName("id")
    val movieId: Int,

    @field:SerializedName("title")
    val movieTitle: String,

    @field:SerializedName("poster_path")
    val posterPath: String,
)
