package com.glennappdev.cornhealthai.naturalenemies

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityEnemiesOfWwBinding
import com.glennappdev.cornhealthai.settings.LocaleHelper
import com.synnapps.carouselview.ImageListener

class EnemiesOfWw : AppCompatActivity() {
    var gb = ArrayList<Int>()
    var rb = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemiesOfWwBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // check saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.descGb.text = resources.getString(R.string.desc_gb)
        binding.benefitGb.text = resources.getString(R.string.benefit_gb)
        binding.descRb.text = resources.getString(R.string.desc_rb)
        binding.benefitRb.text = resources.getString(R.string.benefit_rb)

        gb = arrayListOf(
            R.drawable.gb1,
            R.drawable.gb2,
            R.drawable.gb3)

        rb = arrayListOf(
            R.drawable.rb1,
            R.drawable.rb2,
            R.drawable.rb3)

        binding.carouselGb.pageCount = gb.size
        binding.carouselGb.setImageListener(gbImageListener)

        binding.carouselRb.pageCount = rb.size
        binding.carouselRb.setImageListener(rbImageListener)

    }

    var gbImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(gb[position])
    }
    var rbImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(rb[position])
    }
}