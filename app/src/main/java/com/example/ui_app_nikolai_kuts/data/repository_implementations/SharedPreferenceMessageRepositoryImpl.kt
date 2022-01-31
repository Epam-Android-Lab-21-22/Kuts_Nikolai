package com.example.ui_app_nikolai_kuts.data.repository_implementations

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.domain.repositories.MessageRepository
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageOperationStatus

class SharedPreferenceMessageRepositoryImpl(
    application: Application,
    private val onFinishOperation: (StorageOperationStatus) -> Unit
) : MessageRepository {

    companion object {

        private const val SHARED_PREFERENCE_NAME = "message"
        private const val KEY = "message_key"
        private val DEFAULT_MASSAGE_TEXT_VALUE: String? = null
    }

    private val sharedPreferences =
        application.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun saveMessageInStorage(message: Message) {
        sharedPreferences.edit { putString(KEY, message.text) }
        onFinishOperation(StorageOperationStatus.SUCCESS)
    }

    override fun loadMessageFromStorage(): Message? {
        val text = sharedPreferences.getString(KEY, DEFAULT_MASSAGE_TEXT_VALUE) ?: return null

        onFinishOperation(StorageOperationStatus.SUCCESS)
        return Message(text)
    }
}