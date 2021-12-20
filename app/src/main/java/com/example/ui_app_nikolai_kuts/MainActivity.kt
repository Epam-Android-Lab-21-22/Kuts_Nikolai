package com.example.ui_app_nikolai_kuts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(R.layout.activity_main), OnFragmentClickListener {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    companion object {
            private const val SAVED_TITLE_KEY = "saved_title_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            initializeBackStacks()
            setActionBarTitle(RED_BACK_STACK_NAME)
        }

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.red_nav_item -> onNavigationItemClick(RED_BACK_STACK_NAME)
                R.id.blue_nav_item -> onNavigationItemClick(BLUE_BACK_STACK_NAME)
                R.id.green_nav_item -> onNavigationItemClick(GREEN_BACK_STACK_NAME)
            }
            true
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        title = savedInstanceState.getString(SAVED_TITLE_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_TITLE_KEY, title.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount <= 0) {
            finish()
        } else {
            val updatedBadgeNumber = supportFragmentManager.backStackEntryCount - 1
            binding.bottomNavigationView.manageItemState(updatedBadgeNumber)
        }
    }

    override fun onClick(serialNumber: Int) {
        binding.bottomNavigationView.manageItemState(serialNumber)
    }

    private fun initializeBackStacks() {
        performReplacementTransaction(RedFragment.getInstance(), RED_BACK_STACK_NAME)

        performReplacementTransaction(BlueFragment.getInstance(), BLUE_BACK_STACK_NAME)
        supportFragmentManager.saveBackStack(BLUE_BACK_STACK_NAME)

        performReplacementTransaction(GreenFragment.getInstance(), GREEN_BACK_STACK_NAME)
        supportFragmentManager.saveBackStack(GREEN_BACK_STACK_NAME)
    }

    private fun performReplacementTransaction(fragment: Fragment, backStackName: String) {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(backStackName)
            .commit()
    }

    private fun onNavigationItemClick(backStackName: String) {
        val currentBackStackName = supportFragmentManager.getCurrentBackStackName()

        if (currentBackStackName != backStackName) {
            saveAndRestoreBackStacks(
                savedBackStackName = currentBackStackName,
                restoredBackStackName = backStackName
            )
            setActionBarTitle(backStackName)
        }
    }

    private fun FragmentManager.getCurrentBackStackName(): String {
        val result = if (backStackEntryCount > 0) {
            val lastIndex = backStackEntryCount - 1
            val backstackEntry = getBackStackEntryAt(lastIndex)
            backstackEntry.name
        } else {
            null
        }
        return result.toString()
    }

    private fun saveAndRestoreBackStacks(
        savedBackStackName: String,
        restoredBackStackName: String,
    ) {
        supportFragmentManager.saveBackStack(savedBackStackName)
        supportFragmentManager.restoreBackStack(restoredBackStackName)
    }

    private fun setActionBarTitle(backStackName: String) {
        title = getString(R.string.action_bar_title, backStackName)
    }

    private fun BottomNavigationView.manageItemState(expectedNumber: Int) {
        menu.forEach { menuItem ->
            if (menuItem.isChecked) {

                getOrCreateBadge(menuItem.itemId).also  { badge ->
                    if (expectedNumber <= 0) {
                        badge.isVisible = false
                    } else {
                        badge.isVisible = true
                        badge.number = expectedNumber
                    }
                }
            }
        }
    }
}