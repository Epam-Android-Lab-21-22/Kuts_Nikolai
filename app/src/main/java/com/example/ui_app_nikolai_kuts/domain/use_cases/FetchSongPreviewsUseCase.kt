package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.entities.SongPreview
import com.example.ui_app_nikolai_kuts.domain.repositories.SongPreviewRepository

class FetchSongPreviewsUseCase(private val repository: SongPreviewRepository) {

    operator fun invoke(): Set<SongPreview> = repository.fetchSongPreviews()
}