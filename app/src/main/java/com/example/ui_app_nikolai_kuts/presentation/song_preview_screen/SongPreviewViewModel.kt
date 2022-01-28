package com.example.ui_app_nikolai_kuts.presentation.song_preview_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui_app_nikolai_kuts.data.repository_implementation.SongPreviewRepositoryImpl
import com.example.ui_app_nikolai_kuts.domain.entities.SongPreview
import com.example.ui_app_nikolai_kuts.domain.repositories.SongPreviewRepository
import com.example.ui_app_nikolai_kuts.domain.use_cases.FetchSongPreviewsUseCase

class SongPreviewViewModel : ViewModel(), ISongPreviewViewModel {

    private val repository: SongPreviewRepository = SongPreviewRepositoryImpl()
    private val fetchSongPreviewsUseCase = FetchSongPreviewsUseCase(repository)

    override val data: MutableLiveData<Set<SongPreview>> by lazy {
        MutableLiveData<Set<SongPreview>>(fetchSongPreviewsUseCase())
    }
}