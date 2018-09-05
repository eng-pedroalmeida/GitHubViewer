package com.pedroalmeida.githubviewer.model

import android.os.Parcel
import android.os.Parcelable

class GitHubUser(val login: String?,
                 val name: String?,
                 val avatar_url: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(name)
        parcel.writeString(avatar_url)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<GitHubUser> {
        override fun createFromParcel(parcel: Parcel): GitHubUser {
            return GitHubUser(parcel)
        }

        override fun newArray(size: Int): Array<GitHubUser?> {
            return arrayOfNulls(size)
        }
    }

}