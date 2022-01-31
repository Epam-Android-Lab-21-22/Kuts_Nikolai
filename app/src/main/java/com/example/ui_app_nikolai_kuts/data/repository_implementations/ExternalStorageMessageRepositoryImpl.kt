package com.example.ui_app_nikolai_kuts.data.repository_implementations

import android.app.Application
import android.os.Environment
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

class ExternalStorageMessageRepositoryImpl(
    private val application: Application,
    private val onFinishOperation: (operationStatus: StorageOperationStatus) -> Unit,
) : MessageRepository {

    companion object {

        private const val FILE_CHILD_NAME = "message"
    }

    override fun saveMessageInStorage(message: Message) {
        try {
            ifStorageWritable(
                onWritable = {
                    val serializableMessage = message.mapToSerializableMessage()
                    val storage = File(application.externalCacheDir, FILE_CHILD_NAME)
                    val encodedMessage = Json.encodeToString(serializableMessage).toByteArray()

                    if (encodedMessage.size < storage.freeSpace) {
                        storage.writeBytes(array = encodedMessage)
                        onFinishOperation(StorageOperationStatus.SUCCESS)
                    } else {
                        onFinishOperation(StorageOperationStatus.LACKS_FREE_STORAGE_SPACE)
                    }
                },
                onNotWritable = { onFinishOperation(StorageOperationStatus.CANNOT_WRITE) }
            )
        } catch (e: Exception) {
            onFinishOperation(StorageOperationStatus.FAILED)
        }
    }

    override fun loadMessageFromStorage(): Message? {
        return try {
            ifStorageReadable(
                onReadable = {
                    val storage = File(application.externalCacheDir, FILE_CHILD_NAME)

                    storage.inputStream().use { inputStream ->
                        val decodedMessage = inputStream.readBytes().decodeToString()
                        val serializableMessage =
                            Json.decodeFromString<SerializableMessage>(decodedMessage)

                        onFinishOperation(StorageOperationStatus.SUCCESS)
                        serializableMessage.mapToMessage()
                    }
                },
                onUnreadable = {
                    onFinishOperation(StorageOperationStatus.CANNOT_READ)
                    null
                }
            )
        } catch (e: Exception) {
            onFinishOperation(StorageOperationStatus.FAILED)
            null
        }
    }

    private inline fun ifStorageWritable(onWritable: () -> Unit, onNotWritable: () -> Unit) {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) onWritable()
        else onNotWritable()
    }

    private inline fun <R> ifStorageReadable(onReadable: () -> R, onUnreadable: () -> R): R {
        return when (Environment.getExternalStorageState()) {
            Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY -> onReadable()
            else -> onUnreadable()
        }
    }
}