package com.example.ui_app_nikolai_kuts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.first_ui.FirstFragment
import com.example.ui_app_nikolai_kuts.databinding.ActivityMainBinding
import com.example.ui_app_nikolai_kuts.second_ui.SecondFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.navigationBar.setOnItemSelectedListener { menuItem ->
             when (menuItem.itemId) {
                R.id.first_item -> replaceCurrentFragmentTo(FirstFragment::class.java)
                R.id.second_item -> replaceCurrentFragmentTo(SecondFragment::class.java)
            }
            true
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container, FirstFragment::class.java, bundleOf())
        }
    }

    private fun replaceCurrentFragmentTo(fragmentClass: Class<out Fragment>) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragmentClass, bundleOf())
        }
    }
}