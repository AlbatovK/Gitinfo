package com.albatros.gitinfo.domain.use_case

import com.albatros.gitinfo.data.remote.dto.toDomainObject
import com.albatros.gitinfo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRepoDetailsUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(login: String) = withContext(Dispatchers.IO) {
        userRepository.getUserRepos(login).map { it.toDomainObject() }
    }
}