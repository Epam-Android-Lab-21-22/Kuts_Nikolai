package com.example.ui_app_nikolai_kuts.presentation.view_intefaces

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User

interface UserView {

    fun onGetData(users: List<User>)

    fun onDataUpdated(users: List<User>)
}