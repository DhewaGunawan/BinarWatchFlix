package com.example.binarwatchflix.data.network.api.response.cast

import com.google.gson.annotations.SerializedName

class CastResponse(

    @field:SerializedName("cast")
    val cast: List<CastItem>,
)

class CastItem(

    @field:SerializedName("profile_path")
    val profilePath: String,

    @field:SerializedName("name")
    val name: String,

    )