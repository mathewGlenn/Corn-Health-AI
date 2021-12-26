package com.glennappdev.cornhealthai.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityHelpBinding

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHelpBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)


        // checked saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.step1.text = resources.getString(R.string.step1)
        binding.step2.text = resources.getString(R.string.step2)
        binding.step3.text = resources.getString(R.string.step3)
        binding.step4.text = resources.getString(R.string.step4)

        binding.disclaimer.text = resources.getString(R.string.disclaimer)
    }
}