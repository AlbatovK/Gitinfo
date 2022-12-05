package com.albatros.gitinfo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val createdAt: String?,
    val avatarUrl: String?,
    val bio: String?,
    val company: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val publicGists: Int?,
    val publicRepos: Int?,
    val reposUrl: String?,
) : Parcelable