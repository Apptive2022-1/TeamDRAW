package com.example.teamdraw.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.teamdraw.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun userToString(user: User): String {
        return gson.toJson(user)
    }

    @TypeConverter
    fun stringToUser(data: String): User {
        val listType = object : TypeToken<User>() {}.type
        return gson.fromJson(data, listType)
    }
}