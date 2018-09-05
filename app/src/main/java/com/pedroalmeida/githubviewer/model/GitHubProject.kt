package com.pedroalmeida.githubviewer.model

import android.os.Parcel
import android.os.Parcelable

class GitHubProject(val name: String?,
                    val language: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(language)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<GitHubProject> {
        override fun createFromParcel(parcel: Parcel): GitHubProject {
            return GitHubProject(parcel)
        }

        override fun newArray(size: Int): Array<GitHubProject?> {
            return arrayOfNulls(size)
        }
    }

}