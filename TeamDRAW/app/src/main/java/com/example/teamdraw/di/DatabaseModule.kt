package com.example.teamdraw.di

import android.content.Context
import androidx.room.Room
import com.example.teamdraw.data.database.ContestsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ):ContestsDatabase = Room.databaseBuilder(
        context,
        ContestsDatabase::class.java,
        "contests_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database:ContestsDatabase) = database.contestsDao()
}