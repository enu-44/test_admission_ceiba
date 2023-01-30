package com.admission.testceiba.di

import android.content.Context
import androidx.room.Room
import com.admission.testceiba.database.CeibaDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    private const val DATA_BASE_NAME= "ceiba_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CeibaDataBase::class.java, DATA_BASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideUserDao(db:CeibaDataBase) =  db.getUserDao()
}