package com.example.ui_app_nikolai_kuts.data.fake_databases

import android.content.SharedPreferences
import com.example.ui_app_nikolai_kuts.data.common.StorageReadWriter
import com.example.ui_app_nikolai_kuts.data.common.UserStorageReadWriter
import com.example.ui_app_nikolai_kuts.data.common.UserGenerator
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.domain.update

class UserSource private constructor(preferences: SharedPreferences) {

    companion object {

        private const val FIRST_RUNNING_KEY = "first_user_source_running_key"
        private var source: UserSource? = null

        fun getInstance(sharedPreferences: SharedPreferences): UserSource {
            return source ?: synchronized(this) {
                val instance = UserSource(sharedPreferences)
                source = instance
                instance
            }
        }
    }

    private val _data: MutableList<User> = mutableListOf()
    val data: List<User> = _data

    private val userStorageReadWriter: StorageReadWriter<User> = UserStorageReadWriter(preferences)


    init {
        if (preferences.getBoolean(FIRST_RUNNING_KEY, true)) {
            saveInitialData()
            preferences.edit().putBoolean(FIRST_RUNNING_KEY, false).apply()
        }
        _data.update(userStorageReadWriter.readFromStorage())
    }

    fun removeUser(user: User) {
        _data.remove(user)
        userStorageReadWriter.writeIntoStorage(*_data.toTypedArray())
    }

    private fun saveInitialData() {
        val initData = UserGenerator().generateUsers(userQuantity = 20)
        userStorageReadWriter.writeIntoStorage(*initData.toTypedArray())
    }
}