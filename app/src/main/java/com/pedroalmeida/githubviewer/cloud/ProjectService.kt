package com.pedroalmeida.githubviewer.cloud

import com.pedroalmeida.githubviewer.model.GitHubProject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectService {

    @GET("users/{userName}/repos")
    fun getProjects(@Path("userName") userName: String) : Call<List<GitHubProject>>
}