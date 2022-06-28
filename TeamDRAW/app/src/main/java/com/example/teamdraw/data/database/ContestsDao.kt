package com.example.teamdraw.data.database

import androidx.room.*
import com.example.teamdraw.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ContestsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContest(contestEntity: ContestsEntity)

    @Query("SELECT * FROM contest_table ORDER By id ASC")
    fun readContest(): Flow<List<ContestsEntity>>

    @Query("DELETE FROM contest_table ")
    suspend fun deleteAllContest()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table ORDER By id ASC")
    fun readUser(): Flow<List<UserEntity>>

    @Query("DELETE FROM user_table ")
    suspend fun deleteAllUser()
}