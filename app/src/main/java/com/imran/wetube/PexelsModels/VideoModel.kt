package com.imran.wetube.PexelsModels

data class VideoModel(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val prev_page: String,
    val total_results: Int,
    val url: String,
    val videos: ArrayList<Video>
)