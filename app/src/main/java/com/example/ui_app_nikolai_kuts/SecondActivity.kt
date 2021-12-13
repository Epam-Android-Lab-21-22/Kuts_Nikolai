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
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ui_app_nikolai_kuts.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private companion object {
        private const val CAMERA_REQUEST_CODE = 34123432
    }

    private val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.requestPermissionButton.setOnClickListener { onRequestPermissionButtonClick() }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {

        if (requestCode == CAMERA_REQUEST_CODE) {

            if (grantResults.isNotEmpty()) {
                val result = checkPermission(grantResults[0])
                handleCheckPermissionResult(result)

            } else {
                Toast.makeText(this,
                    "onRequestPermissionsResult : Permission denied",
                    Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun onRequestPermissionButtonClick() {
        if (isCameraPermissionGranted()) {
            actionOnCameraPermissionGranted()
        } else {
            requestCameraPermission()
        }
    }

    private fun actionOnCameraPermissionGranted() {
        Toast.makeText(this, "action on permission granted", Toast.LENGTH_SHORT).show()
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
            .setTitle(getString(R.string.denied_foreve_permission_dialog_title))
            .setMessage(getString(R.string.denied_foreve_permission_dialog_message))
            .setPositiveButton(getString(R.string.dinied_forever_permission_dialog_button)) { _, _ ->
                startActivity(intent)
            }
            .create()
            .show()
    }


    private fun isCameraPermissionGranted(): Boolean {
        return when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.M -> true
            isCameraPermissionGrantedOnCheckSelf() -> true
            else -> false
        }
    }

    private fun isCameraPermissionGrantedOnCheckSelf(): Boolean {
        return ContextCompat.checkSelfPermission(
            this@SecondActivity,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermission(grantResult: Int): CheckPermissionResult {
        return when {
            grantResult == PackageManager.PERMISSION_GRANTED -> CheckPermissionResult.GRANTED
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

    private fun showExplanationDialog() {
        AlertDialog.Builder(this@SecondActivity)
            .setTitle(getString(R.string.explanation_dialog_title))
            .setMessage(getString(R.string.explationtion_dialog_message))
            .create()
            .show()
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    enum class CheckPermissionResult {
        GRANTED,
        DENIED,
        DENIED_FOREVER
    }


}