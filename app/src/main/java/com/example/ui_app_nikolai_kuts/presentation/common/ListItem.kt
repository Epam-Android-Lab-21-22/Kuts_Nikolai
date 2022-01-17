package com.example.ui_app_nikolai_kuts.presentation.common

import com.example.ui_app_nikolai_kuts.domain.entities.enums.ItemTypes

interface ListItem {

    fun getListItemType(): ItemTypes
}