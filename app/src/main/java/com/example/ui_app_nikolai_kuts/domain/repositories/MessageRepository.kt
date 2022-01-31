package com.example.ui_app_nikolai_kuts.domain.repositories

import com.example.ui_app_nikolai_kuts.domain.entities.Message

interface MessageRepository {

    fun saveMessageInStorage(message: Message)

    fun loadMessageFromStorage(): Message?
}