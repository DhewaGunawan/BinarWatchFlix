package com.example.binarwatchflix.data.network.api.response.genre

import com.google.gson.annotations.SerializedName
    class GenreResponse(

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: Int
    )
