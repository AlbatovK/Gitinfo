package com.albatros.gitinfo.module

import com.albatros.gitinfo.data.repository.UserRepositoryImpl
import com.albatros.gitinfo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}