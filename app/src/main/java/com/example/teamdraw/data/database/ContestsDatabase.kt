package com.example.teamdraw.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(
    entities = [ContestsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ContestTypeConverter::class)
abstract class ContestsDatabase : RoomDatabase() {
    abstract fun contestsDao(): ContestsDao
}