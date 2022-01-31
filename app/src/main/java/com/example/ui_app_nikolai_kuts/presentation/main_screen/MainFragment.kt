package com.example.ui_app_nikolai_kuts.presentation.main_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.databinding.FragmentMainBinding
import com.example.ui_app_nikolai_kuts.domain.entities.Message
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageMarker
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageMarker.*
import com.example.ui_app_nikolai_kuts.presentation.main_screen.enums.StorageOperationStatus.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: IMainViewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        setClickListeners()
    }

    private fun subscribeObservers() {
        viewModel.data.observe(viewLifecycleOwner) { message: Message? ->
            message?.let { binding.messageTextView.text = it.text }
        }

        viewModel.storageOperationStatus.observe(viewLifecycleOwner) { storageOperationStatus ->
            storageOperationStatus ?: return@observe
            showToast(getString(storageOperationStatus.description))
        }
    }

    private fun setClickListeners() {
        with(binding) {
            sharedPreferencesSavingButton.setSavingClickListener(marker = SHARED_PREFERENCES)
            internalStorageSavingButton.setSavingClickListener(marker = INTERNAL)
            externalStorageSavingButton.setSavingClickListener(marker = EXTERNAL)
            databaseSavingButton.setSavingClickListener(marker = DATABASE)

            sharedPreferencesLoadingButton.setLoadingClickListener(marker = SHARED_PREFERENCES)
            internalStorageLoadingButton.setLoadingClickListener(marker = INTERNAL)
            externalStorageLoadingButton.setLoadingClickListener(marker = EXTERNAL)
            databaseLoadingButton.setLoadingClickListener(marker = DATABASE)
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun Button.setSavingClickListener(marker: StorageMarker) {
        this.setOnClickListener {
            saveMessage(marker)
            binding.messageEditText.text?.clear()
        }
    }

    private fun Button.setLoadingClickListener(marker: StorageMarker) {
        this.setOnClickListener { viewModel.loadMessage(marker) }
    }

    private fun saveMessage(marker: StorageMarker) {
        viewModel.saveMessage(
            messageText = binding.messageEditText.text.toString().trim(),
            marker = marker
        )
    }

}