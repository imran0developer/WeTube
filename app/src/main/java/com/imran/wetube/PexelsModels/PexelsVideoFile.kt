package com.imran.wetube.PexelsModels

data class PexelsVideoFile(
    val file_type: String,
    val fps: Double,
    val height: Int,
    val id: Int,
    val link: String,
    val quality: String,
    val width: Int
)