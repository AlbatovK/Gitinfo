package com.albatros.gitinfo.domain.use_case

import com.albatros.gitinfo.data.remote.dto.toDomainObject
import com.albatros.gitinfo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(login: String) = withContext(Dispatchers.IO) {
        userRepository.getUser(login).toDomainObject()
    }
}