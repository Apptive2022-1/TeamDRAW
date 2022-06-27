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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContestsViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    private val contestList = mutableListOf<Contest>()

    init {
//        inputContest(
//            0L,
//            "아이더 로고 디자인 공모전",
//            listOf("디자인", "사진", "예술", "영상"),
//            "2022. 01. 08 ~ 2022. 01. 30",
//            listOf("아이더", "그라폴리오"),
//            "최대 100만원",
//            """
//                YOUNG OUTDOOR EIDER
//                아이더 제품에 잘 어울릴만한 로고를 디자인해주세요
//
//                접수기간 : 1.08(수) ~ 1.30(목)
//                투표기간 : 1.08(수)~ 2.06(목)
//                당첨 발표 : 2.13(목)
//
//                대상(1 명) : 500만원
//                우수상(5 명) : 100만원
//                    """
//        )
        val contest1 = Contest(
            0L,
            "아이더 로고 디자인 공모전",
            "디자인    사진  예술  영상",
            "2022. 01. 08 ~ 2022. 01. 30",
            listOf("아이더", "그라폴리오"),
            "최대 100만원",
            """
                YOUNG OUTDOOR EIDER
                아이더 제품에 잘 어울릴만한 로고를 디자인해주세요

                접수기간 : 1.08(수) ~ 1.30(목)
                투표기간 : 1.08(수)~ 2.06(목)
                당첨 발표 : 2.13(목)

                대상(1 명) : 500만원
                우수상(5 명) : 100만원
            """.trimIndent()
        )
        val contest2 = Contest(
            1L,
            "ICT    스마트 디바이스    전국  공모전",
            "개발 기획 디자인 ICT",
            "2022. 5. 20 ~ 6. 30",
            listOf("과학기술정보통신부"),
            "최대 5천만원",
            """
                현재 상용화되지 않은 DNA와 관련된 데이터
                현재 상용화되지 않은 DNA와 관련된 데이터
                현재 상용화되지 않은 DNA와 관련된 데이터
                현재 상용화되지 않은 DNA와 관련된 데이터
                현재 상용화되지 않은 DNA와 관련된 데이터
                현재 상용화되지 않은 DNA와 관련된 데이터
                현재 상용화되지 않은 DNA와 관련된 데이터
            """.trimIndent()
        )

        contestList.add(contest1)
        contestList.add(contest2)

        insertContest()
    }

    val readRecipes: LiveData<List<ContestsEntity>> = repository.local.readContests().asLiveData()

//    private fun inputContest(
//        contestId: Long, title: String, fieldList: List<String>, period: String,
//        organList: List<String>, reward: String, detail: String
//    ) {
//        contestList.add(
//            Contest(
//                contestId = contestId,
//                title = title,
//                field = fieldList,
//                period = period,
//                organization = organList,
//                reward = reward,
//                detail = detail
//            )
//        )
//    }

    private fun insertContest() {
        contestList.forEach { contest, ->
            val contestsEntity = ContestsEntity(contest)
            viewModelScope.launch {
                repository.local.insertContest(contestsEntity)
            }
        }
    }
}