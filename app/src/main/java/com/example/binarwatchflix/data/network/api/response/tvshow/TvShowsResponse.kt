package com.example.binarwatchflix.data.network.api.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @field:SerializedName("results")
    val results: List<TvShowList>,
)

class TvShowList(
    @field:SerializedName("id")
    val tvShowId: Int,

    @field:SerializedName("name")
    val tvShowTitle: String,

    @field:SerializedName("first_air_date")
    val tvFirstAirDate: String,

    @field:SerializedName("poster_path")
    val posterPath: String,
)
