package com.imran.wetube.PexelsModels

data class PexelsVideo(
    val avg_color: Any,
    val duration: Int,
    val full_res: Any,
    val height: Int,
    val id: Int,
    val image: String,
    val tags: List<Any>,
    val url: String,
    val user: PexelsUser,
    val video_files: List<PexelsVideoFile>,
    val video_pictures: List<PexelsVideoPicture>,
    val width: Int
)