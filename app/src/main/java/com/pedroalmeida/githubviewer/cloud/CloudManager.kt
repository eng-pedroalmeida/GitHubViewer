package com.pedroalmeida.githubviewer.cloud

import com.pedroalmeida.githubviewer.model.GitHubProject
import com.pedroalmeida.githubviewer.model.GitHubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CloudManager {

    private val retrofit = RetrofitConfig()

    /**
     * Get User Profile from GitHub API
     */
    fun getUserProfile(userName: String, onResponse: (success: Boolean, user: GitHubUser?) -> Unit) {
        val call = retrofit.profileService().getUser(userName)
        call.enqueue(object: Callback<GitHubUser?> {
            override fun onFailure(call: Call<GitHubUser?>?, t: Throwable?) {
                onResponse(false, null)
            }

            override fun onResponse(call: Call<GitHubUser?>?, response: Response<GitHubUser?>?) {
                onResponse(true, response?.body())
            }

        })
    }

    /**
     * Get a list of User Repositories from GitHub API
     */
    fun getUserRepos(userName: String, onResponse: (success: Boolean, repos: List<GitHubProject>?) -> Unit) {
        val call = retrofit.projectService().getProjects(userName)
        call.enqueue(object : Callback<List<GitHubProject>?> {
            override fun onFailure(call: Call<List<GitHubProject>?>, t: Throwable) {
                onResponse(false, null)
            }

            override fun onResponse(call: Call<List<GitHubProject>?>, response: Response<List<GitHubProject>?>) {
                onResponse(true, response.body())
            }

        })
    }
}