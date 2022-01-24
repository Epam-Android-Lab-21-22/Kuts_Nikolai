package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.entities.Song
import com.example.ui_app_nikolai_kuts.domain.repositories.SongRepository

class FetchSongUseCase(private val repository: SongRepository) {

    operator fun invoke(id: String): Song? = repository.fetchSong(id)

}