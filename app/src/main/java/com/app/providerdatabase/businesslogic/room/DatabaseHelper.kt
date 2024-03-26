package com.app.providerdatabase.businesslogic.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.providerdatabase.pojo.UserModel
import com.app.providerdatabase.businesslogic.room.dao.DaoUser

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun daoUser(): DaoUser

    companion object {
        private var INSTANCE: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context?): DatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx!!.applicationContext,
                    DatabaseHelper::class.java,
                    "UserDatabase"
                ).build()

                INSTANCE = instance
                instance
            }


        }


    }


}