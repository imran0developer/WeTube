package com.imran.wetube.Api_Package

import com.imran.wetube.Api_Package.ApiClient.Companion.API_KEY
import com.imran.wetube.PexelsModels.VideoModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.http.Headers
import retrofit2.http.Query

public interface ApiSet {

    @Headers("Authorization:$API_KEY")
    @GET("popular")
    fun getVideos(
    @Query("page") page: Int,
    @Query("per_page") perPage: Int
//    ): Response<VideoModel>
    ): Call<VideoModel?>
}