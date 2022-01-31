package com.example.ui_app_nikolai_kuts.data.repository_implementations

import android.app.Application
import com.example.ui_app_nikolai_kuts.data.entities.SerializableMessage
import com.example.ui_app_nikolai_kuts.data.mapToMessage
import com.example.ui_app_nikolai_kuts.data.mapToSerializableMessage
import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.domain.repositories.MessageRepository
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageOperationStatus
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class InternalStorageMessageRepositoryImpl(
    private val application: Application,
    private val onFinishOperation: (StorageOperationStatus) -> Unit,
) : MessageRepository {

    companion object {

        private const val FILE_CHILD_NAME = "message"
    }

    override fun saveMessageInStorage(message: Message) {
        try {
            val serializableMessage = message.mapToSerializableMessage()
            val encodedMessage = Json.encodeToString(serializableMessage).toByteArray()
            val storage = File(application.filesDir, FILE_CHILD_NAME)

            if (encodedMessage.size < storage.freeSpace) {
                storage.writeBytes(array = encodedMessage)
                onFinishOperation(StorageOperationStatus.SUCCESS)
            } else {
                onFinishOperation(StorageOperationStatus.CANNOT_WRITE)
            }
        } catch (e: Exception) {
            onFinishOperation(StorageOperationStatus.FAILED)
        }
    }

    override fun loadMessageFromStorage(): Message? {
        return try {
            File(application.filesDir, FILE_CHILD_NAME).inputStream().use { inputSteam ->
                val decodedMessage = inputSteam.readBytes().decodeToString()
                val serializableMessage = Json.decodeFromString<SerializableMessage>(decodedMessage)

                serializableMessage.mapToMessage()
            }
        } catch (e: Exception) {
            onFinishOperation(StorageOperationStatus.FAILED)
            null
        }
    }
}