package com.example.teamdraw.models

data class UserData(
    val userId: String = "",
    val name: String? = null,
    val nickname: String? = null,
    val sex: String? = null,
    val city: String? = null, // 시/도
    val region: String? = null, // 지역구
    val univ: String? = null,
    val univ_email: String? = null,
    val major:String? = null,
    val grade:String? = null
)
