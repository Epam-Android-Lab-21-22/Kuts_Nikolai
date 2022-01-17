package com.example.ui_app_nikolai_kuts.domain.use_cases

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor
import com.example.ui_app_nikolai_kuts.domain.repositories.ItemColorRepository

class GetItemColors(private val repository: ItemColorRepository) {

    operator fun invoke(): List<ItemColor> = repository.getItemColors()
}