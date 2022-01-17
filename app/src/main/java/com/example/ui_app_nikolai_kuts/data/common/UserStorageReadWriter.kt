package com.example.ui_app_nikolai_kuts.data.common

import android.content.SharedPreferences
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User

class UserStorageReadWriter(
    private val preferences: SharedPreferences,
) : StorageReadWriter<User> {

    companion object {

        private const val KEY = "user_key"
        private const val DEFAULT_VALUE = "empty"
    }

    override fun writeIntoStorage(vararg data: User) {
        preferences.edit()
            .putString(KEY, data.toList().toString())
            .apply()
    }

    override fun readFromStorage(): List<User> {
        val usersAsString = preferences.getString(KEY, DEFAULT_VALUE) ?: DEFAULT_VALUE
        return if (usersAsString != DEFAULT_VALUE) {
            UserParser(usersAsString).parseUsers()
        } else {
            emptyList()
        }
    }
}