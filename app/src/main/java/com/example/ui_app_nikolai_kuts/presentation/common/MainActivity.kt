package com.example.ui_app_nikolai_kuts.presentation.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.ui_app_nikolai_kuts.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_UI_App_Nikolai_Kuts)
        setContentView(R.layout.activity_main)
    }
}