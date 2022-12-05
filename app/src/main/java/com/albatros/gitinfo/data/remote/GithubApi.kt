package com.albatros.gitinfo.data.remote

import com.albatros.gitinfo.data.remote.dto.GithubRepoDetailsDto
import com.albatros.gitinfo.data.remote.dto.GithubUserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET(value = "/users/{login}")
    suspend fun getUserInfo(@Path("login") login: String): GithubUserDto

    @GET(value = "/users/{login}/repos")
    suspend fun getRepoDetails(@Path("login") login: String): List<GithubRepoDetailsDto>
}