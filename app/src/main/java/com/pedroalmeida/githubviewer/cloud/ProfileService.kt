package com.pedroalmeida.githubviewer.cloud

import com.pedroalmeida.githubviewer.model.GitHubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {

    @GET("users/{userName}")
    fun getUser(@Path("userName") userName: String) : Call<GitHubUser>
}