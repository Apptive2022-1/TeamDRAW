package com.example.teamdraw.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContestsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContest(contestEntity: ContestsEntity)

    @Query("SELECT * FROM contest_table ORDER By id ASC")
    fun readContest(): Flow<List<ContestsEntity>>
}