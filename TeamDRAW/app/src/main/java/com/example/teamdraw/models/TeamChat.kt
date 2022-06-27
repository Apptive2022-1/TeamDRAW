package com.example.teamdraw.models

data class TeamChat(
    val teamID : String = "",
    val chatList : MutableList<Chat> = mutableListOf()
)