package com.admission.testceiba.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey()
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "email")  val email: String,
    @ColumnInfo(name = "phone")  val phone: String
)

