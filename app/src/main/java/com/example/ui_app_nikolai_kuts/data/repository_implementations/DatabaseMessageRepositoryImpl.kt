package com.example.ui_app_nikolai_kuts.data.repository_implementations

import android.app.Application
import com.example.ui_app_nikolai_kuts.data.databases.RoomMessageDatabase
import com.example.ui_app_nikolai_kuts.data.mapToMessage
import com.example.ui_app_nikolai_kuts.data.mapToRoomMessage
import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.domain.repositories.MessageRepository
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageOperationStatus
import java.lang.Exception

class DatabaseMessageRepositoryImpl(
    application: Application,
    private val onFinishOperation: (StorageOperationStatus) -> Unit,
) : MessageRepository {

    private val database = RoomMessageDatabase.getInstance(application)

    override fun saveMessageInStorage(message: Message) {
        try {
            database.MessageDao().insertMessage(message = message.mapToRoomMessage())
            onFinishOperation(StorageOperationStatus.SUCCESS)
        } catch (e: Exception) {
            onFinishOperation(StorageOperationStatus.FAILED)
        }
    }

    override fun loadMessageFromStorage(): Message? {
        return try {
            val roomMessage = database.MessageDao().getMessage()

            onFinishOperation(StorageOperationStatus.SUCCESS)
            roomMessage?.mapToMessage()
        } catch (e: Exception) {
            onFinishOperation(StorageOperationStatus.FAILED)
            null
        }
    }
}