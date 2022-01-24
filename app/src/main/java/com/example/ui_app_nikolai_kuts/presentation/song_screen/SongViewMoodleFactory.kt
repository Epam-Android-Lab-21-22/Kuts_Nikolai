package com.example.ui_app_nikolai_kuts.presentation.song_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SongViewMoodleFactory(private val songId: String) : ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongViewModel(songId) as T
    }
}