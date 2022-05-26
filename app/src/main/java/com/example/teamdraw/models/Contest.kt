package com.example.teamdraw.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contest(
    val contestId : Long = 0L,
    val title : String,
    val field : List<String>,
    val period : String,
    val organization :List<String>,
    val reward : String,
    val detail : String,
):Parcelable