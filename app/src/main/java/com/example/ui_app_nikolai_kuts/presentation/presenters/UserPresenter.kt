package com.example.ui_app_nikolai_kuts.presentation.presenters

import android.content.SharedPreferences
import com.example.ui_app_nikolai_kuts.data.repository_implementations.UserRepositoryImpl
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.domain.repositories.UserRepository
import com.example.ui_app_nikolai_kuts.domain.use_cases.RemoveUser
import com.example.ui_app_nikolai_kuts.domain.use_cases.GetUsers
import com.example.ui_app_nikolai_kuts.presentation.view_intefaces.UserView

class UserPresenter(private val view: UserView, sharedPreference: SharedPreferences) {

    private val repository: UserRepository = UserRepositoryImpl(sharedPreference)
    private val getUsers = GetUsers(repository)
    private val removeUser = RemoveUser(repository)

    fun loadData() {
        view.onGetData(users = getUsers())
    }

    fun deleteUser(user: User) {
        removeUser(user)
        view.onDataUpdated(users = repository.getUsers())
    }
}