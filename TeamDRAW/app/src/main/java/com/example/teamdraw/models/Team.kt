package com.example.teamdraw.models

data class Team(
    val teamID : String = "",
    val userList : MutableList<String>? = null,
    val teamName : String= "",
    val teamNotice : String = "",
    val forTarget : String= "",
    val teamLeader : String = ""
)
