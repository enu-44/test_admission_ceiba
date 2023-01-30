package com.admission.testceiba.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.admission.testceiba.database.dao.UserDao
import com.admission.testceiba.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class CeibaDataBase: RoomDatabase() {
    abstract fun getUserDao():UserDao
}