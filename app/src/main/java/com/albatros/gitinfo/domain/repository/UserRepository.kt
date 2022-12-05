package com.albatros.gitinfo.domain.repository

import com.albatros.gitinfo.data.remote.dto.GithubRepoDetailsDto
import com.albatros.gitinfo.data.remote.dto.GithubUserDto

interface UserRepository {
    suspend fun getUser(login: String): GithubUserDto
    suspend fun getUserRepos(login: String): List<GithubRepoDetailsDto>
}