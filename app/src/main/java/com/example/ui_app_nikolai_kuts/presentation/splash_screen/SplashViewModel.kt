package com.example.ui_app_nikolai_kuts.presentation.splash_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel(), ISplashViewModel {

    companion object {

        private const val INITIAL_PROGRESS_VALUE = 0
        const val MAX_PROGRESS_VALUE = 100
        private const val DELAY_VALUE: Long = 20
    }

    private var progress = INITIAL_PROGRESS_VALUE

    override val barProgress: MutableLiveData<Int> = MutableLiveData(progress)

    override fun startLoading() {
        viewModelScope.launch {
            for (percent in INITIAL_PROGRESS_VALUE..MAX_PROGRESS_VALUE) {
                delay(DELAY_VALUE)
                progress = percent
                barProgress.value = progress
            }
        }
    }
}