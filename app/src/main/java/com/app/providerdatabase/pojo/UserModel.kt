package com.app.providerdatabase.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")

data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val user:String,
    val department:String
)