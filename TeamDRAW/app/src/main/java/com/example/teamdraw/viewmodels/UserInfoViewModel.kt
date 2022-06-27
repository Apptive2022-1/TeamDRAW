package com.example.teamdraw.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _nickname = MutableLiveData<String>()
    private val _univ = MutableLiveData<String>()
    private val _univEmail = MutableLiveData<String>()
    private val _grade = MutableLiveData<String>()
    private val _sex = MutableLiveData<String>()
    private val _major = MutableLiveData<String>()
    private val _local = MutableLiveData<String>()
    private val _emailAuthenticated = MutableLiveData<String>()
    private val _departureList = MutableLiveData<MutableList<String>>()
    private val _positionList = MutableLiveData<MutableList<String>>()
    private val _positionDetailList = MutableLiveData<MutableList<String>>()
    private val _selfIntroduce = MutableLiveData<String>()
    private val _personalLink = MutableLiveData<String>()
    private val _teamList = MutableLiveData<MutableList<String>>()


    val name: MutableLiveData<String>
        get() = _name

    val nickname: MutableLiveData<String>
        get() = _nickname

    val univ: MutableLiveData<String>
        get() = _univ

    val univEmail: MutableLiveData<String>
        get() = _univEmail

    val grade: MutableLiveData<String>
        get() = _grade

    val sex: MutableLiveData<String>
        get() = _sex

    val major: MutableLiveData<String>
        get() = _major

    val local: MutableLiveData<String>
        get() = _local

    val emailAuthenticated: MutableLiveData<String>
        get() = _emailAuthenticated

    val departureList: MutableLiveData<MutableList<String>>
        get() = _departureList

    val positionList: MutableLiveData<MutableList<String>>
        get() = _positionList
    val positionDetailList: MutableLiveData<MutableList<String>>
        get() = _positionDetailList

    val selfItroduce: MutableLiveData<String>
        get() = _selfIntroduce

    val personalLink: MutableLiveData<String>
        get() = _personalLink

    val teamList: MutableLiveData<MutableList<String>>
        get() = _teamList

    init {
        _emailAuthenticated.value = "false"
        _departureList.value = mutableListOf()
        _positionList.value = mutableListOf()
        _positionDetailList.value = mutableListOf()
        _teamList.value= mutableListOf()
    }

    fun updateValue(value: String, member: String) {
        when (member) {
            "NAME" -> {
                _name.value = value
            }
            "NICKNAME" -> {
                _nickname.value = value
            }
            "UNIV" -> {
                _univ.value = value
            }
            "UNIV_EMAIL" -> {
                _univEmail.value = value
            }
            "SEX" -> {
                _sex.value = value
            }
            "GRADE" -> {
                _grade.value = value
            }
            "MAJOR" -> {
                _major.value = value
            }
            "LOCAL" -> {
                _local.value = value
            }
            "AUTHENTICATE" -> {
                _emailAuthenticated.value = value
            }
            "SELFINTRODUCE"->{
                _selfIntroduce.value = value
            }
            "PERSONALLINK"->{
                _personalLink.value = value
            }
        }
    }
    fun addList(value:String, member : String){
        when(member){
            "DEPARTURE" ->{
                _departureList.value?.add(value)
            }
            "POSITION" ->{
                _positionList.value?.add(value)
            }
            "POSITION_DETAIL" ->{
                _positionDetailList.value?.add(value)
            }
            "TEAMLIST" ->{
                _teamList.value?.add(value)
            }

        }
    }
    fun removeList(value:String, member : String){
        when(member){
            "DEPARTURE" ->{
                _departureList.value?.remove(value)
            }
            "POSITION" ->{
                _positionList.value?.remove(value)
            }
            "POSITION_DETAIL" ->{
                _positionDetailList.value?.remove(value)
            }
        }
    }


}

