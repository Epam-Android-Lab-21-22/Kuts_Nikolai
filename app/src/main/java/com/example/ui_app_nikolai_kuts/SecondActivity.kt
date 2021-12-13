package com.example.ui_app_nikolai_kuts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.ui_app_nikolai_kuts.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private val cameraPermissionRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGetCameraPermissionResult
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.useCameraButton.setOnClickListener { onUseCameraButtonClick() }
    }

    private fun onUseCameraButtonClick() {
        cameraPermissionRequestLauncher.launch(Manifest.permission.CAMERA)
    }

    private fun onGetCameraPermissionResult(isGranted: Boolean?) {
        if (isGranted != null) {
            val result = checkPermission(isGranted)
            handleCheckPermissionResult(result)

        } else {
           showToast(getString(R.string.it_is_impossible_to_use_camera_message))
        }
    }

    private fun checkPermission(isGranted: Boolean): CheckPermissionResult {
        return when {
            isGranted -> CheckPermissionResult.GRANTED
            isNotCameraPermissionDeniedForever() -> CheckPermissionResult.DENIED
            else -> CheckPermissionResult.DENIED_FOREVER
        }
    }

    private fun isNotCameraPermissionDeniedForever(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
    }

    private fun handleCheckPermissionResult(result: CheckPermissionResult) {
        when (result) {
            CheckPermissionResult.GRANTED -> actionOnCameraPermissionGranted()
            CheckPermissionResult.DENIED -> showExplanationDialog()
            CheckPermissionResult.DENIED_FOREVER -> askUserForOpeningAppSettings()
        }
    }

    private fun actionOnCameraPermissionGranted() {
        showToast(getString(R.string.camera_using_toast_message))
    }

    private fun showExplanationDialog() {
        AlertDialog.Builder(this@SecondActivity)
            .setTitle(getString(R.string.explanation_dialog_title))
            .setMessage(getString(R.string.explanation_dialog_message))
            .create()
            .show()
    }

    private fun askUserForOpeningAppSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )

        if (
            packageManager.resolveActivity(
                appSettingsIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            ) == null
        ) {
            showToast(getString(R.string.denied_forever_permission_toast))
        } else {
            showDeniedForeverPermissionDialog(appSettingsIntent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showDeniedForeverPermissionDialog(intent: Intent) {
        AlertDialog.Builder(this@SecondActivity)
            .setTitle(getString(R.string.denied_forever_permission_dialog_title))
            .setMessage(getString(R.string.denied_forever_permission_dialog_message))
            .setPositiveButton(getString(R.string.denied_forever_permission_dialog_button)) { _, _ ->
                startActivity(intent)
            }
            .create()
            .show()
    }
}