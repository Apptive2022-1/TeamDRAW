package com.example.teamdraw.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.teamdraw.data.Repository
import com.example.teamdraw.data.database.ContestsEntity
import com.example.teamdraw.data.database.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindingTeamViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    val readUsers : LiveData<List<UserEntity>> = repository.local.readUsers().asLiveData()

}