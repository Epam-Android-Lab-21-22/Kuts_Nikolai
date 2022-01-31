package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.domain.repositories.MessageRepository

class SaveMassageInStorageUseCase {

    operator fun invoke(message: Message, repository: MessageRepository) {
        repository.saveMessageInStorage(message)
    }
}