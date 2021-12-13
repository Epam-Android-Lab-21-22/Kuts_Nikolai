package com.example.ui_app_nikolai_kuts

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ui_app_nikolai_kuts.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val netWorkCallback = MainNetworkCallback(
            onNetworkAvailable = ::onAvailable,
            onNetworkLost = ::onLost
        )

        onFirstNetworkCheck(connectivityManager)

        registerNetworkCallback(connectivityManager, netWorkCallback)

        binding.toSecondActivityButton.setOnClickListener { onToSecondActivityButtonClick() }
    }


    private fun onFirstNetworkCheck(manager: ConnectivityManager) {
        if (isNotNetworkAvailable(manager)) {
            binding.toSecondActivityButton.isEnabled = false
            showSnackBar(getString(R.string.no_network_message))
        }
    }

    private fun registerNetworkCallback(
        manager: ConnectivityManager,
        networkCallback: NetworkCallback,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            manager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            manager.registerNetworkCallback(networkRequest, networkCallback)
        }
    }

    private fun onToSecondActivityButtonClick() {
        Intent(this@MainActivity, SecondActivity::class.java).apply intent@{
            startActivity(this@intent)
        }
    }

    private fun isNotNetworkAvailable(manager: ConnectivityManager): Boolean {
        val isNetworkAvailable = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val network: Network? = manager.activeNetwork
                val capabilities: NetworkCapabilities? =
                    manager.getNetworkCapabilities(network)

                capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
            }
            else -> {
                @Suppress("DEPRECATION")
                manager.activeNetworkInfo?.isConnectedOrConnecting ?: false
            }
        }
        return !isNetworkAvailable
    }

    private fun onAvailable() {
        runOnUiThread {
            binding.toSecondActivityButton.isEnabled = true
            showSnackBar(getString(R.string.there_is_network_message))
        }
    }

    private fun onLost() {
        runOnUiThread {
            binding.toSecondActivityButton.isEnabled = false
            showSnackBar(getString(R.string.no_network_message))
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).apply {
            animationMode = ANIMATION_MODE_SLIDE
            show()
        }
    }
}