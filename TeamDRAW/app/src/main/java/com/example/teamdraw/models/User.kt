package com.example.teamdraw.models

data class User(
    val userId: String = "",
    val name: String? = null,
    val nickname: String? = null,
    val sex: String? = null,
    val local: String? = null,
    val univ: String? = null,
    val univ_email: String? = null,
    val major:String? = null,
    val grade:String? = null,
    val emailAuthenticated: String? = null,
    val departureList: MutableList<String>?  = null,
    val positionList: MutableList<String>?  = null,
    val positionDetailList: MutableList<String>? = null,
    val selfIntroduce:String? = null,
    val personalLink: String? = null,
    val questionList: MutableList<String>?  = null,
    val skillList: MutableList<String>? = null,
    val skillH: String? = null,
    val skillM: String? = null,
    val skillL: String? = null,
)
