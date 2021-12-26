package com.glennappdev.cornhealthai.naturalenemies

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityEnemiesOfFawBinding
import com.glennappdev.cornhealthai.settings.LocaleHelper
import com.synnapps.carouselview.ImageListener

class EnemiesOfFaw : AppCompatActivity() {
    var tw = ArrayList<Int>()
    var pw = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemiesOfFawBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // check saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.descPw.text = resources.getString(R.string.desc_pw)
        binding.benefitPw.text = resources.getString(R.string.benefit_pw)
        binding.descTw.text = resources.getString(R.string.desc_tw)
        binding.benefitTw.text = resources.getString(R.string.benefit_tw)

        tw = arrayListOf(
            R.drawable.tw1,
            R.drawable.tw2,
            R.drawable.tw3)

        pw = arrayListOf(
            R.drawable.pw1,
            R.drawable.pw2,
            R.drawable.pw3)

        binding.carouselTw.pageCount = tw.size
        binding.carouselTw.setImageListener(twImageListener)

        binding.carouselPw.pageCount = pw.size
        binding.carouselPw.setImageListener(pwImageListener)
    }

    var twImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(tw[position])
    }
    var pwImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(pw[position])
    }
}