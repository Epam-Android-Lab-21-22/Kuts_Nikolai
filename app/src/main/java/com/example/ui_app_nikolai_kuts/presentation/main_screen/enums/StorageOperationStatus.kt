package com.example.ui_app_nikolai_kuts.presentation.main_screen.enums

import androidx.annotation.StringRes
import com.example.ui_app_nikolai_kuts.R

enum class StorageOperationStatus(@StringRes val description: Int) {

    SUCCESS(R.string.status_description_success),
    FAILED(R.string.status_description_failed),
    LACKS_FREE_STORAGE_SPACE(R.string.status_description_cannot_write),
    CANNOT_WRITE(R.string.status_description_cannot_write),
    CANNOT_READ(R.string.status_description_cannot_read),
}