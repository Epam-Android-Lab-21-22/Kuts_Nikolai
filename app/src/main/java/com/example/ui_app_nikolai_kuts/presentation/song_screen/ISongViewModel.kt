package com.example.ui_app_nikolai_kuts.presentation.song_screen

import androidx.lifecycle.LiveData
import com.example.ui_app_nikolai_kuts.domain.entities.Song

interface ISongViewModel {

    val data: LiveData<Song?>
}