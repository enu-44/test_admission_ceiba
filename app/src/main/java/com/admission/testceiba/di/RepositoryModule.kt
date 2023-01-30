package com.admission.testceiba.di

import com.admission.testceiba.data.PostRepository
import com.admission.testceiba.data.UserRepository
import com.admission.testceiba.domain.repository.IPostRepository
import com.admission.testceiba.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepository): IUserRepository

    @Binds
    abstract fun bindPostRepository(postRepositoryImpl: PostRepository): IPostRepository
}