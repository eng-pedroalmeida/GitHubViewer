package com.pedroalmeida.githubviewer.presenter

import com.pedroalmeida.githubviewer.cloud.CloudManager
import com.pedroalmeida.githubviewer.view.IView

class ProfilePresenter(private val view: IView.Profile,
                       private val userName: String): IPresenter.Profile {

    private val cloudConnection = CloudManager()

    override fun loadProfileData() {
        cloudConnection.getUserProfile(userName) { success, user ->
            if (!success) {
                view.showError()
                return@getUserProfile
            }

            user?.let {
                view.showUserInfo(it)
                loadUserProjects()
            } ?: run {
                view.showUserNotFound()
            }
        }
    }

    private fun loadUserProjects() {
        cloudConnection.getUserRepos(userName) { success, repos ->
            if (!success) {
                view.showError()
                return@getUserRepos
            }

            repos?.let {
                view.showUserRepos(repos)
            } ?: kotlin.run {
                view.showError()
            }
        }
    }

}