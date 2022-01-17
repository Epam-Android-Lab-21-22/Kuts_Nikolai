package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.repositories.ItemColorRepository

class CreateItemColor(private val repository: ItemColorRepository) {

    operator fun invoke() {
        repository.createItemColor()
    }
}