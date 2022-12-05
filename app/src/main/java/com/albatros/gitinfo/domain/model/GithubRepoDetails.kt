package com.albatros.gitinfo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepoDetails(
    val createdAt: String?,
    val defaultBranch: String?,
    val description: String?,
    val forksCount: Int?,
    val fullName: String?,
    val language: String?,
    val name: String?,
    val openIssuesCount: Int?,
    val owner: GithubUser,
    val size: Int?,
    val stargazersCount: Int?,
    val topics: List<String>?,
    val watchersCount: Int?,
) : Parcelable