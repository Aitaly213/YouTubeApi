package com.geektech.youtubeapi.model


import com.google.gson.annotations.SerializedName

class Standard(
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0
)