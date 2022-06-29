package com.example.teamdraw.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contest(
    val contestId : Long = 0L,
    val title : String,
    val field : String,
    val period : String,
    val organization :List<String>,
    val reward : String,
    val detail : String,
    val type : Int = 0
):Parcelable