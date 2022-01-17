package com.example.ui_app_nikolai_kuts.domain.repositories

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User

interface UserRepository {

    fun getUsers(): List<User>

    fun deleteUser(user: User)
}