package com.admission.testceiba.domain.repository

import com.admission.testceiba.domain.model.UserDom

interface IUserRepository {
    suspend fun getRemoteUsers(): List<UserDom>
    suspend fun getLocalUsers(): List<UserDom>
    suspend fun getLocalUserById(userId:Int): UserDom
    suspend fun saveLocalUsers(users:List<UserDom>)
    suspend fun searchLocalUsersByName(query:String): List<UserDom>
}