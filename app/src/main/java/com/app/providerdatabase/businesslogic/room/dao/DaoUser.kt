package com.app.providerdatabase.businesslogic.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.providerdatabase.pojo.UserModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DaoUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserModel): Long

    @Update
    fun update(user: UserModel): Completable

    @Query("DELETE FROM Users WHERE id=:id")
    fun delete(id: Array<out String>?): Int

    @Query("delete from Users")
    fun deleteAllUsers()

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Single<List<UserModel>>


}