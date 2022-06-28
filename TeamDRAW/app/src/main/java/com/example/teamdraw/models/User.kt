package com.example.teamdraw.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
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
    val emailAuthenticated : String? = null,
    val departureList : @RawValue MutableList<String>?  = null,
    val positionList : @RawValue MutableList<String>?  = null,
    val positionDetailList : @RawValue MutableList<String>? = null,
    val selfIntroduce:String? = null,
    val personalLink:String? = null,
    val teamList : @RawValue MutableList<String>? = null,
    val one_to_one_ChatList : @RawValue MutableList<String>? = null
): Parcelable
