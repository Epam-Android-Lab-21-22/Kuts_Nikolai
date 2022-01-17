package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.domain.repositories.UserRepository

class RemoveUser(private val repository: UserRepository) {

    operator fun invoke(user: User) {
        repository.deleteUser(user)
    }
}