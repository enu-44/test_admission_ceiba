package com.admission.testceiba.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.admission.testceiba.database.entities.UserEntity

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM user_table")
    suspend fun findAll():List<UserEntity>

    @Query(value = "SELECT * FROM user_table WHERE id = :id")
    suspend fun findById(id:Int):UserEntity

    @Query(value = "SELECT * FROM user_table WHERE name LIKE :search")
    suspend fun findAllByName(search:String ):List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(quotes:List<UserEntity>)
}