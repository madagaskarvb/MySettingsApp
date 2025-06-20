package com.example.mysettingsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Добавляем MenuProvider для управления меню
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_settings -> {
                        openSettingsFragment()
                        true
                    }
                    R.id.menu_about -> {
                        showAboutDialog()
                        true
                    }
                    R.id.menu_exit -> {
                        finish()
                        true
                    }
                    else -> false
                }
            }
        }, this, Lifecycle.State.RESUMED)
    }

    private fun openSettingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SettingsFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun showAboutDialog() {
        Toast.makeText(
            this,
            "MySettingsApp v1.0\nПример приложения с MenuProvider и PreferenceFragmentCompat",
            Toast.LENGTH_LONG
        ).show()
    }
}
