package com.example.teamdraw.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teamdraw.data.Repository
import com.example.teamdraw.data.database.ContestsEntity
import com.example.teamdraw.models.Contest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContestsViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    init {
        insertContest()
    }

    val readRecipes: LiveData<List<ContestsEntity>> = repository.local.readContests().asLiveData()

    private fun insertContest() {
        val contest = Contest(
            contestId = 0L,
            title = "아이더 로고 디자인 공모전",
            field = listOf("디자인", "사진", "예술", "영상"),
            period = "2022. 01. 08 ~ 2022. 01. 30",
            organization = listOf("아이더", "그라폴리오"),
            reward = "최대 100만원",
            detail = """
                YOUNG OUTDOOR EIDER
                아이더 제품에 잘 어울릴만한 로고를 디자인해주세요
                    
                접수기간 : 1.08(수) ~ 1.30(목)
                투표기간 : 1.08(수)~ 2.06(목)
                당첨 발표 : 2.13(목)
                    
                대상(1 명) : 500만원
                우수상(5 명) : 100만원
                    """
        )
        val contestsEntity = ContestsEntity(contest)
        viewModelScope.launch {
            repository.local.insertContest(contestsEntity)
        }

    }
}