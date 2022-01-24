package com.example.ui_app_nikolai_kuts.presentation.splash_screen

import androidx.lifecycle.LiveData

interface ISplashViewModel {

    val barProgress: LiveData<Int>

    fun startLoading()
}