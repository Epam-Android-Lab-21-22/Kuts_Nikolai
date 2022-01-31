package com.example.ui_app_nikolai_kuts.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ui_app_nikolai_kuts.data.entities.RoomMessage
import com.example.ui_app_nikolai_kuts.data.entities.MESSAGE_TABLE_NAME

@Dao
interface MessageDao {

    @Query("SELECT * FROM $MESSAGE_TABLE_NAME")
    fun getMessage(): RoomMessage?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: RoomMessage)
}