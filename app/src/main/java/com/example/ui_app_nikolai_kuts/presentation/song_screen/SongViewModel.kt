package com.example.ui_app_nikolai_kuts.presentation.song_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui_app_nikolai_kuts.data.repository_implementation.SongRepositoryImpl
import com.example.ui_app_nikolai_kuts.domain.entities.Song
import com.example.ui_app_nikolai_kuts.domain.repositories.SongRepository
import com.example.ui_app_nikolai_kuts.domain.use_cases.FetchSongUseCase

class SongViewModel(songId: String) : ViewModel(),ISongViewModel {

    private val repository: SongRepository = SongRepositoryImpl()
    private val fetchSongUseCase = FetchSongUseCase(repository)

    override val data: MutableLiveData<Song?> = MutableLiveData<Song?>(fetchSongUseCase(songId))
}