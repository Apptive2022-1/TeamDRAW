package com.example.teamdraw.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teamdraw.models.User

@Entity(tableName = "user_table")
class UserEntity(
    @Embedded
    var user:User
) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}