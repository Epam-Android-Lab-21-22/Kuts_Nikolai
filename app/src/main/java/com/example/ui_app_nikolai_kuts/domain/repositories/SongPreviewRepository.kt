package com.example.ui_app_nikolai_kuts.domain.repositories

import com.example.ui_app_nikolai_kuts.domain.entities.SongPreview

interface SongPreviewRepository {

    fun fetchSongPreviews(): Set<SongPreview>
}