package com.example.ui_app_nikolai_kuts.domain.entities

data class Song (
    val id: String,
    val title: String,
    val performer: String,
    val coverUrl: String,
    val lyrics: String,
    val year: Int,
    val album: String,
    val genre: String
)