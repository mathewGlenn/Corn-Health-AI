package com.glennappdev.cornhealthai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        val view: View = binding.root
        setContentView(view)

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

    }
}