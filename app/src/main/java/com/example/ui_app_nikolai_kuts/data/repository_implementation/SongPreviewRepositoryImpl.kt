package com.example.ui_app_nikolai_kuts.data.repository_implementation

import com.example.ui_app_nikolai_kuts.data.databases.SongPreviewDatabase
import com.example.ui_app_nikolai_kuts.domain.entities.SongPreview
import com.example.ui_app_nikolai_kuts.domain.repositories.SongPreviewRepository

class SongPreviewRepositoryImpl : SongPreviewRepository {

    override fun fetchSongPreviews(): Set<SongPreview> = SongPreviewDatabase.data
}