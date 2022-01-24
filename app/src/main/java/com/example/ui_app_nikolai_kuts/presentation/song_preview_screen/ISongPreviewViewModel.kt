package com.example.ui_app_nikolai_kuts.presentation.song_preview_screen

import androidx.lifecycle.LiveData
import com.example.ui_app_nikolai_kuts.domain.entities.SongPreview

interface ISongPreviewViewModel {

    val data: LiveData<Set<SongPreview>>
}