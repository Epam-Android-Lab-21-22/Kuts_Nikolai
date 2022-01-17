package com.example.ui_app_nikolai_kuts.presentation.view_intefaces

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor

interface ItemColorView {

    fun onGetData(itemColors: List<ItemColor>)

    fun onDataUpdated(itemColors: List<ItemColor>)
}