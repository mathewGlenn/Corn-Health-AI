package com.glennappdev.cornhealthai.naturalenemies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityEnemiesOfFawBinding
import com.synnapps.carouselview.ImageListener

class EnemiesOfFaw : AppCompatActivity() {
    var tw = ArrayList<Int>()
    var pw = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemiesOfFawBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

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