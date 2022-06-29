package com.example.teamdraw.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teamdraw.data.Repository
import com.example.teamdraw.data.database.ContestsEntity
import com.example.teamdraw.data.database.UserEntity
import com.example.teamdraw.models.User
import com.example.teamdraw.util.ProvideDataContest
import com.example.teamdraw.util.ProvideUserData
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
        insertUsers(ProvideUserData.userList)
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
        viewModelScope.launch {
            repository.local.deleteContent()
            ProvideDataContest.allList.forEach { contest ->
                val contestsEntity = ContestsEntity(contest)
                repository.local.insertContest(contestsEntity)
            }
        }
    }


    fun clickProject() {
        viewModelScope.launch {
            repository.local.deleteContent()
            ProvideDataContest.projectList.forEach {
                repository.local.insertContest(ContestsEntity(it))
            }
        }
    }

    fun clickContest() {
        viewModelScope.launch {
            repository.local.deleteContent()
            ProvideDataContest.contestList.forEach {
                repository.local.insertContest(ContestsEntity(it))
            }
        }
    }

    fun clickAll() {
        viewModelScope.launch {
            repository.local.deleteContent()
            ProvideDataContest.allList.forEach {
                repository.local.insertContest(ContestsEntity(it))
            }
        }
    }
}