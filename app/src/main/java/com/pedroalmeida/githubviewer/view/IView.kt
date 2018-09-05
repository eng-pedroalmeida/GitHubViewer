package com.pedroalmeida.githubviewer.view

import com.pedroalmeida.githubviewer.model.GitHubProject
import com.pedroalmeida.githubviewer.model.GitHubUser

interface IView {

    interface Profile {
        fun showUserInfo(user: GitHubUser)
        fun showUserRepos(repos: List<GitHubProject>)
        fun showUserNotFound()
        fun showError()
    }
}