package com.pedroalmeida.githubviewer.cloud

import com.pedroalmeida.githubviewer.util.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    fun projectService(): ProjectService = retrofit.create(ProjectService::class.java)
    fun profileService(): ProfileService = retrofit.create(ProfileService::class.java)
}