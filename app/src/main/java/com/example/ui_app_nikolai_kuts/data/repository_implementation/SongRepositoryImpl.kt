package com.example.ui_app_nikolai_kuts.data.repository_implementation

import com.example.ui_app_nikolai_kuts.data.databases.SongDatabase
import com.example.ui_app_nikolai_kuts.domain.entities.Song
import com.example.ui_app_nikolai_kuts.domain.repositories.SongRepository

class SongRepositoryImpl : SongRepository {

    override fun fetchSong(id: String): Song? = SongDatabase.data.find { song -> song.id == id }
}