package com.glennappdev.cornhealthai

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.glennappdev.cornhealthai.databinding.ActivitySettingsBinding
import com.jakewharton.processphoenix.ProcessPhoenix

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.cardLanguage.setOnClickListener {
            showAlertDialog()

        }

        binding.cardAboutUs.setOnClickListener {
            startActivity(Intent(this, AboutUs::class.java))
        }
    }

    private fun showAlertDialog() {
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val savedLanguage = preferences.getString("SAVED_LANGUAGE", "en")

        var language = "en"
        var checkedItem = 0

        if (savedLanguage == "en") {
            language = "en"
            checkedItem = 0

        } else if (savedLanguage == "tl") {
            language = "tl"
            checkedItem = 1
        }

        val localeHelper = LocaleHelper()

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Change language")
        val items = arrayOf("English", "Filipino")

        alertDialogBuilder.setSingleChoiceItems(items, checkedItem) { _, which ->
            when (which) {
                0 -> {
                    language = "en"
                }
                1 -> {
                    language = "tl"
                }
            }
        }
        alertDialogBuilder.setPositiveButton("Save"
        ) { dialog, _ ->
            localeHelper.changeLocale(applicationContext, language)
            dialog.dismiss()
            showPromptRestartApp()
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun showPromptRestartApp() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Restart app for language change to take effect.")
        alertDialogBuilder.setNegativeButton("Restart later"
        ) { dialog, _ ->
            dialog.cancel()
        }
        alertDialogBuilder.setPositiveButton("Restart now"
        ){_,_ ->
            ProcessPhoenix.triggerRebirth(applicationContext)
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}