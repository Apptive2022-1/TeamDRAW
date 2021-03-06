package com.example.teamdraw.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.teamdraw.models.Contest
import com.example.teamdraw.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ContestTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun contestToString(contest: Contest): String {

        return gson.toJson(contest) // Json으로 바꿔줌
    }

    @TypeConverter
    fun stringToContest(data: String): Contest {
        val listType = object : TypeToken<Contest>() {}.type
        return gson.fromJson(data, listType)
    }
}