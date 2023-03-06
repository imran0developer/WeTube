package com.imran.wetube.Api_Package

import com.imran.wetube.Models.MsgModel
import com.imran.wetube.Models.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST

interface ApiSet {
    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("dp") dp: String
    ) : Call<MsgModel>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<UserModel>
}