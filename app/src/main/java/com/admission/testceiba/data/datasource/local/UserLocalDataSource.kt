package com.admission.testceiba.data.datasource.local

import com.admission.testceiba.database.dao.UserDao
import com.admission.testceiba.database.entities.UserEntity
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao
)  {
    suspend fun findById(id:Int): UserEntity = userDao.findById(id)
    suspend fun findAll(): List<UserEntity> = userDao.findAll()
    suspend fun saveAll(users:List<UserEntity>)= userDao.saveAll(users)
    suspend fun findAllUsersByName(query:String)= userDao.findAllByName("%$query%")
}