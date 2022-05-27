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
    val isEmailAuthenticated : String? = null,
    val departureList : MutableList<String>?  = null,
    val positionList : MutableList<String>?  = null
)
