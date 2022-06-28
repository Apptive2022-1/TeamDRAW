package com.example.teamdraw.models

data class OneToOneChat (
    val chatId :String? = null,
    val host : MutableList<String> = mutableListOf(),
    val chatList : MutableList<Chat> = mutableListOf()
)
