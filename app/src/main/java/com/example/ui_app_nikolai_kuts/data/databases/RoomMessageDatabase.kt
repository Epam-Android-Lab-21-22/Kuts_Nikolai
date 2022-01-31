package com.example.ui_app_nikolai_kuts.data.databases

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ui_app_nikolai_kuts.data.daos.MessageDao
import com.example.ui_app_nikolai_kuts.data.entities.RoomMessage


@Database(entities = [RoomMessage::class], version = 2)
abstract class RoomMessageDatabase : RoomDatabase() {

    companion object {

        private const val DB_NAME = "message.db"
        private var database: RoomMessageDatabase? = null

        fun getInstance(application: Application): RoomMessageDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application,
                    RoomMessageDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                database = instance
                instance
            }
        }
    }

    abstract fun MessageDao(): MessageDao
}