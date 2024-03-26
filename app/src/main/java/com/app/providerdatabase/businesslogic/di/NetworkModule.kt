package com.app.providerdatabase.businesslogic.di

import android.content.Context
import com.app.providerdatabase.businesslogic.room.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRoomDB(context: Context?): DatabaseHelper {

        return DatabaseHelper.getInstance(context)
    }
}