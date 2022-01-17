package com.example.ui_app_nikolai_kuts.data.repository_implementations

import android.content.SharedPreferences
import com.example.ui_app_nikolai_kuts.data.fake_databases.UserSource
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.domain.repositories.UserRepository

class UserRepositoryImpl(preferences: SharedPreferences) : UserRepository {

    private val dataSource = UserSource.getInstance(preferences)

    override fun getUsers(): List<User> = dataSource.data

    override fun deleteUser(user: User) {
        dataSource.removeUser(user)
    }
}