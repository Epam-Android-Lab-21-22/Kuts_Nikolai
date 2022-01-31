package com.example.ui_app_nikolai_kuts.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val MESSAGE_TABLE_NAME = "room_messages"

@Entity(tableName = MESSAGE_TABLE_NAME)
data class RoomMessage(
    @ColumnInfo(name = "text")
    val text: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
)