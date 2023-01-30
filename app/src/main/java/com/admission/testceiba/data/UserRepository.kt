package com.admission.testceiba.data

import com.admission.testceiba.data.datasource.local.UserLocalDataSource
import com.admission.testceiba.data.datasource.remote.UserRemoteDataSource
import com.admission.testceiba.data.mappers.toDom
import com.admission.testceiba.data.mappers.toEntity
import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource:UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) :IUserRepository {

    override suspend fun getRemoteUsers(): List<UserDom> = userRemoteDataSource.getUsers()
        .map { it.toDom() }

    override suspend fun getLocalUsers(): List<UserDom> = userLocalDataSource.findAll()
        .map { it.toDom() }

    override suspend fun getLocalUserById(userId: Int): UserDom =
        userLocalDataSource.findById(userId).toDom()

    override suspend fun saveLocalUsers(users:List<UserDom>) =
        userLocalDataSource.saveAll(users.map { it.toEntity() })

    override suspend fun getLocalUsersByName(query: String):List<UserDom> =
        userLocalDataSource.findAllUsersByName(query).map { it.toDom() }
}