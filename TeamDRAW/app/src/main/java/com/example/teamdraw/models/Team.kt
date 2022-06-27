package com.example.teamdraw.models

data class Team(
    val teamID : String = "",
    val userList : MutableList<String>? = null,
    val teamName : String= "",
    val teamNotice : String? = null,
    val forTarget : String= ""
)
