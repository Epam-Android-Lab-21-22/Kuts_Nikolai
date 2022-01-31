package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.domain.repositories.MessageRepository

class LoadMessageFromStorageUseCase {

    operator fun invoke(repository: MessageRepository): Message? {
        return repository.loadMessageFromStorage()
    }
}