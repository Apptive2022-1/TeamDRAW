package com.example.teamdraw.inputinformation

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
    private val _city = MutableLiveData<String>()
    private val _region = MutableLiveData<String>()

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

    val city: MutableLiveData<String>
        get() = _city
    val region: MutableLiveData<String>
        get() = _region

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
            "CITY" -> {
                _city.value = value
            }
            "REGION" -> {
                _region.value = value
            }
        }
    }


}