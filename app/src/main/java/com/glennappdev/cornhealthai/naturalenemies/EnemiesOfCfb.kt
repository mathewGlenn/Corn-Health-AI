package com.glennappdev.cornhealthai.naturalenemies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityEnemiesOfCfbBinding
import com.synnapps.carouselview.ImageListener

class EnemiesOfCfb : AppCompatActivity() {
    var bw = ArrayList<Int>()
    var tf = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemiesOfCfbBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

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