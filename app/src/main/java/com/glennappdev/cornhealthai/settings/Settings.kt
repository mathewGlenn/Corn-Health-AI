package com.glennappdev.cornhealthai.settings

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.BuildConfig
import com.glennappdev.cornhealthai.databinding.ActivitySettingsBinding
import com.jakewharton.processphoenix.ProcessPhoenix
import java.lang.Exception
import android.widget.Toast
import com.glennappdev.cornhealthai.databinding.ActivityHelpBinding


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

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.cardShareApp.setOnClickListener{
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\nLet me recommend you this application\n\n"
                shareMessage =
                    """
                    ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                    """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

        binding.cardContactUs.setOnClickListener{
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/rfc822"
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("glenngarmaappdev@gmail.com"))
            try {
                startActivity(Intent.createChooser(i, "Send mail..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(this,
                    "There are no email clients installed.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        binding.cardHelp.setOnClickListener{
            startActivity(Intent(this, Help::class.java))
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