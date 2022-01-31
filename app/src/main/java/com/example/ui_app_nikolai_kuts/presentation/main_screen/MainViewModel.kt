package com.example.ui_app_nikolai_kuts.presentation.main_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ui_app_nikolai_kuts.data.repository_implementations.DatabaseMessageRepositoryImpl
import com.example.ui_app_nikolai_kuts.data.repository_implementations.ExternalStorageMessageRepositoryImpl
import com.example.ui_app_nikolai_kuts.data.repository_implementations.InternalStorageMessageRepositoryImpl
import com.example.ui_app_nikolai_kuts.data.repository_implementations.SharedPreferenceMessageRepositoryImpl
import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.domain.repositories.MessageRepository
import com.example.ui_app_nikolai_kuts.domain.use_cases.LoadMessageFromStorageUseCase
import com.example.ui_app_nikolai_kuts.domain.use_cases.SaveMassageInStorageUseCase
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageMarker
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageMarker.*
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageOperationStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application), IMainViewModel {

    override val data: MutableLiveData<Message?> = MutableLiveData()
    override val storageOperationStatus: MutableLiveData<StorageOperationStatus> = MutableLiveData()

    private val saveMassageInStorage = SaveMassageInStorageUseCase()
    private val loadMessageFromStorage = LoadMessageFromStorageUseCase()

    private val sharedPreferenceRepository: MessageRepository =
        SharedPreferenceMessageRepositoryImpl(
            application,
            onFinishOperation = ::setStorageOperationStatus
        )

    private val internalStorageRepository: MessageRepository =
        InternalStorageMessageRepositoryImpl(
            application,
            onFinishOperation = ::setStorageOperationStatus
        )

    private val externalStorageMessageRepository: MessageRepository =
        ExternalStorageMessageRepositoryImpl(
            application,
            onFinishOperation = ::setStorageOperationStatus
        )

    private val databaseRepository: MessageRepository =
        DatabaseMessageRepositoryImpl(
            application,
            onFinishOperation = ::setStorageOperationStatus
        )

    override fun saveMessage(messageText: String, marker: StorageMarker) {
        val message = Message(messageText)

        when (marker) {
            SHARED_PREFERENCES -> message.saveInStorage(repository = sharedPreferenceRepository)
            INTERNAL -> message.saveInStorage(repository = internalStorageRepository)
            EXTERNAL -> message.saveInStorage(repository = externalStorageMessageRepository)
            DATABASE -> message.saveInStorage(repository = databaseRepository)
        }
    }

    override fun loadMessage(marker: StorageMarker) {
        when (marker) {
            SHARED_PREFERENCES -> loadData(repository = sharedPreferenceRepository)
            INTERNAL -> loadData(repository = internalStorageRepository)
            EXTERNAL -> loadData(repository = externalStorageMessageRepository)
            DATABASE -> loadData(repository = databaseRepository)
        }
    }

    private fun setStorageOperationStatus(operationStatus: StorageOperationStatus) {
        viewModelScope.launch { storageOperationStatus.value = operationStatus }
    }

    private fun Message.saveInStorage(repository: MessageRepository) {
        viewModelScope.launch(Dispatchers.IO) {
            saveMassageInStorage(message = this@saveInStorage, repository)
        }
    }

    private fun loadData(repository: MessageRepository) {
        viewModelScope.launch {
            data.value = withContext(Dispatchers.IO) { loadMessageFromStorage(repository) }
        }
    }
}