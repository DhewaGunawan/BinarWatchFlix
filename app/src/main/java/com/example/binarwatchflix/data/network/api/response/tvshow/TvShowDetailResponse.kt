package com.example.binarwatchflix.data.network.api.response.tvshow

import com.example.binarwatchflix.data.network.api.response.genre.GenreResponse
import com.google.gson.annotations.SerializedName

class TvShowDetailResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("genres")
    val genres: List<GenreResponse>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("backdrop_path")
    val posterPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    )