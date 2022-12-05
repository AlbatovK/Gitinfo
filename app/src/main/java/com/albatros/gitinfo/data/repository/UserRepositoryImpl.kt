package com.albatros.gitinfo.data.repository

import com.albatros.gitinfo.data.remote.GithubApi
import com.albatros.gitinfo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: GithubApi) : UserRepository {

    override suspend fun getUser(login: String) = api.getUserInfo(login)

    override suspend fun getUserRepos(login: String) = api.getRepoDetails(login)

}