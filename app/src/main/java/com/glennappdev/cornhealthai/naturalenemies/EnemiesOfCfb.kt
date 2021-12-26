package com.glennappdev.cornhealthai.naturalenemies

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityEnemiesOfCfbBinding
import com.glennappdev.cornhealthai.settings.LocaleHelper
import com.synnapps.carouselview.ImageListener

class EnemiesOfCfb : AppCompatActivity() {
    var bw = ArrayList<Int>()
    var tf = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemiesOfCfbBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // check saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.descBw.text = resources.getString(R.string.desc_bw)
        binding.benefitBw.text = resources.getString(R.string.benefit_bw)
        binding.descTf.text = resources.getString(R.string.desc_tf)
        binding.benefitTf.text = resources.getString(R.string.benefit_tf)

        bw = arrayListOf(
            R.drawable.bw1,
            R.drawable.bw2,
            R.drawable.bw3,
            R.drawable.bw4)

        tf = arrayListOf(
            R.drawable.tf1,
            R.drawable.tf2,
            R.drawable.tf3)

        binding.carouselBw.pageCount = bw.size
        binding.carouselBw.setImageListener(bwImageListener)

        binding.carouselTf.pageCount = tf.size
        binding.carouselTf.setImageListener(tfImageListener)

    }
    var bwImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(bw[position])
    }
    var tfImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(tf[position])
    }
}