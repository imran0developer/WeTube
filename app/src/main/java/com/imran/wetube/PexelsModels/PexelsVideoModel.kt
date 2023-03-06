package com.imran.wetube.PexelsModels

import com.google.gson.annotations.SerializedName

data class PexelsVideoModel(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val prev_page: String,
    val total_results: Int,
    val url: String,

    @SerializedName("videos")
    val pexelsVideos: ArrayList<PexelsVideo>
)