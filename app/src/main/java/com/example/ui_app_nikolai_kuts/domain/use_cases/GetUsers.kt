package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.domain.repositories.UserRepository

class GetUsers(private val repository: UserRepository) {

    operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}