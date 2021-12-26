package com.glennappdev.cornhealthai.naturalenemies

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityEnemiesOfCaBinding
import com.glennappdev.cornhealthai.settings.LocaleHelper
import com.synnapps.carouselview.ImageListener

class EnemiesOfCa : AppCompatActivity() {
    var lbb = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemiesOfCaBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // check saved locale language
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.descLbb.text = resources.getString(R.string.desc_lbb)
        binding.benefitLbb.text = resources.getString(R.string.benefit_lbb)

        lbb = arrayListOf(
            R.drawable.lbb1,
            R.drawable.lbb2)

        binding.carouselLbb.pageCount = lbb.size
        binding.carouselLbb.setImageListener(lbbImageListener)
    }

    var lbbImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(lbb[position])
    }
}