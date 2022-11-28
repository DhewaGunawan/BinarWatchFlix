package com.example.binarwatchflix.data.network.api.response.movie
import com.example.binarwatchflix.data.network.api.response.genre.GenreResponse
import com.google.gson.annotations.SerializedName

class MovieDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("genres")
    val genres: List<GenreResponse>,

    @field:SerializedName("original_title")
    val name: String,

    @field:SerializedName("backdrop_path")
    val posterPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("runtime")
    val runTime: Int,

    )