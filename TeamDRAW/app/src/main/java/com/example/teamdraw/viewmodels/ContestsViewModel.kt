package com.example.teamdraw.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teamdraw.data.Repository
import com.example.teamdraw.data.database.ContestsEntity
import com.example.teamdraw.data.database.UserEntity
import com.example.teamdraw.models.Contest
import com.example.teamdraw.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContestsViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {
    init {
        insertContest()
        createUsers()
    }

    val readRecipes: LiveData<List<ContestsEntity>> = repository.local.readContests().asLiveData()


    private fun createUsers() {
        val user1 = User(
            userId = "abc",
            name = "신성현",
            nickname = "까마귀",
            sex = "남성",
            positionList = mutableListOf("개발자"),
            selfIntroduce = " 안녕하세요 신성현입니다. 잘부탁드립니다 열심히하겠습니다~~!~!~!!~!~!~!~!"
        )
        val user2 = User(
            userId = "bcd",
            name = "정수현",
            nickname = "까치",
            sex = "여성",
            positionList = mutableListOf("개발자"),
            selfIntroduce = "까치가 좋아요오ㅗ오ㅗ오오오오"
        )
        val user3 = User(
            userId = "edas",
            name = "김잼민",
            nickname = "잼민이",
            sex = "남성",
            positionList = mutableListOf("디자이너"),
            selfIntroduce = " 잼민이 잼민 김잼민"
        )
        val user4 = User(
            userId = "wqe",
            name = "주다은",
            nickname = "다은주",
            sex = "여성",
            positionList = mutableListOf("기획자"),
            selfIntroduce = " 주다은주다은주다은주다은주다은주다은주다은주다은주다은주다은주다은주다은"
        )
        val user5 = User(
            userId = "joo",
            name = "이주빈",
            nickname = "joo",
            sex = "여성",
            positionList = mutableListOf("기획자"),
            selfIntroduce = "이이이이이이이ㅣ이이이잊우주주주주줒비비비니비ㅣㄴ비빈빈비니비"
        )
        val user6 = User(
            userId = "qzzz",
            name = "최이경",
            nickname = "lleee",
            sex = "여성",
            positionList = mutableListOf("기획자"),
            selfIntroduce = " 누가 이 코드 보냐?"
        )
        val user7 = User(
            userId = "yibhj",
            name = "김연호",
            nickname = "YOONIHO",
            sex = "남성",
            positionList = mutableListOf("디자이너"),
            selfIntroduce = "셀프셀프인트로듀스슷드스드ㅡㅅ디스듸슷디ㅡㅅ드스스스슷스스스"
        )

        val userList = listOf<User>(user1, user2, user3, user4, user5, user6, user7)
        insertUsers(userList)

    }

    private fun insertUsers(userList: List<User>) {
        viewModelScope.launch {
            repository.local.deleteUser()
            userList.forEach {
                repository.local.insertUser(UserEntity(it))
            }
        }
    }

    private fun insertContest() {
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
        val contestsEntity = ContestsEntity(contest1)
        viewModelScope.launch {
            repository.local.deleteContent()
            repository.local.insertContest(contestsEntity)
        }

    }
}