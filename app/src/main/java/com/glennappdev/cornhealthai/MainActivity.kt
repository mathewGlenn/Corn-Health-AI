package com.glennappdev.cornhealthai

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.databinding.ActivityMainBinding
import com.glennappdev.cornhealthai.library.Library
import com.glennappdev.cornhealthai.prediction.ObtainImage
import com.glennappdev.cornhealthai.settings.Help
import com.glennappdev.cornhealthai.settings.LocaleHelper
import com.glennappdev.cornhealthai.settings.Settings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // checked saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        if (language == "tl"){
            binding.txtLeafDisease.textSize = 17f
            binding.txtInsectPest.textSize = 17f

        }

        binding.txtInsectPest.text = resources.getString(R.string.detect_insect)
        binding.txtLeafDisease.text = resources.getString(R.string.detect_disease)

        binding.cardLeafDisease.setOnClickListener {
            val intent = Intent(this, ObtainImage::class.java)
            intent.putExtra("model", "leaf_disease")
            startActivity(intent)
        }
        binding.cardInsectPest.setOnClickListener {
            val intent = Intent(this, ObtainImage::class.java)
            intent.putExtra("model", "insect_pest")
            startActivity(intent)
        }

        binding.cardSettings.setOnClickListener {
            startActivity(Intent(this, Settings::class.java))
        }

        binding.cardLibrary.setOnClickListener {
            startActivity(Intent(this, Library::class.java))
        }

        binding.buttonHelp.setOnClickListener{
            startActivity(Intent(this, Help::class.java))
        }



    }

    override fun onResume() {
        super.onResume()
    }


}