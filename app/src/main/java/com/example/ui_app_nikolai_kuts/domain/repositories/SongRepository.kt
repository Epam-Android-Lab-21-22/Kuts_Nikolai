package com.example.ui_app_nikolai_kuts.domain.repositories

import com.example.ui_app_nikolai_kuts.domain.entities.Song

interface SongRepository {

    fun fetchSong(id: String): Song?
}