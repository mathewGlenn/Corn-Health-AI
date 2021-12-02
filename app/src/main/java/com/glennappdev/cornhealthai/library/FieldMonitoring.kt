package com.glennappdev.cornhealthai.library

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityFieldMonitoringBinding
import com.glennappdev.cornhealthai.settings.LocaleHelper

class FieldMonitoring : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFieldMonitoringBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // set opacity of cardBar to 0
        val topBar = binding.cardBar
        val textBar = binding.txtBar
        topBar.alpha = 0f
        textBar.alpha = 0f
        // while scrolling up, opacity of cardBar increases up to original opacity
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            val maxDistance = binding.imgTop.height
            val movement = binding.scrollView.scrollY
            val alphaFactor: Float = ((movement * 1.0f) / (maxDistance - topBar.height))
            if (movement in 0..maxDistance) {
                topBar.alpha = alphaFactor
                textBar.alpha = alphaFactor
            }
        }

        // check saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.title1.text = resources.getString(R.string.fm_title1)
        binding.title2.text = resources.getString(R.string.fm_title2)
        binding.title3.text = resources.getString(R.string.fm_title3)

        binding.content1.text = resources.getString(R.string.visit_field)
        binding.content2.text = resources.getString(R.string.check_spots)
        binding.content3.text = resources.getString(R.string.look_signs)

        binding.howToMonitor.text = resources.getString(R.string.how_to_monitor)

        binding.useAppFeature.text = resources.getString(R.string.use_app_feature)

    }
}