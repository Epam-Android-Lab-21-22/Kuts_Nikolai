package com.example.ui_app_nikolai_kuts.presentation.main_screen

import androidx.lifecycle.LiveData
import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageMarker
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageOperationStatus

interface IMainViewModel {

    val data: LiveData<Message?>
    val storageOperationStatus: LiveData<StorageOperationStatus>

    fun saveMessage(messageText: String, marker: StorageMarker)

    fun loadMessage(marker: StorageMarker)
}