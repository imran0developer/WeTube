package com.imran.wetube.Api_Package

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PexelsApiClient {
    companion object{
        val BASE_URL = "https://api.pexels.com/videos/"
        const val API_KEY = "kAfPHIf5LHfehESMYA80TsLwhAiqvPAEz8adEkDCoDy3eeJJVPlhkz76"

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}