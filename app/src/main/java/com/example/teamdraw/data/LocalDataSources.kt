package com.example.teamdraw.data

import com.example.teamdraw.data.database.ContestsDao
import com.example.teamdraw.data.database.ContestsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSources @Inject constructor(
    private val contestsDao: ContestsDao
) {
    fun readContests() : Flow<List<ContestsEntity>> = contestsDao.readContest()

    suspend fun insertContest(contestsEntity: ContestsEntity){
        contestsDao.insertContest(contestsEntity)
    }
}