package com.example.teamdraw.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson


class StringListTypeConverter() {
    var gson = Gson()

    @TypeConverter
    fun listToJson(value: List<String>?): String? {
        return if (value != null)
            gson.toJson(value) else null
    }

    @TypeConverter
    fun jsonToList(value: String?): List<String>? {
        return if(value != null) gson.fromJson(value, Array<String>::class.java).toList() else listOf("")
    }
}