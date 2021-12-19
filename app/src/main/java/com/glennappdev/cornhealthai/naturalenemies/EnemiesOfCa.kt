package com.glennappdev.cornhealthai.naturalenemies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityEnemiesOfCaBinding
import com.synnapps.carouselview.ImageListener

class EnemiesOfCa : AppCompatActivity() {
    var lbb = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemiesOfCaBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

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