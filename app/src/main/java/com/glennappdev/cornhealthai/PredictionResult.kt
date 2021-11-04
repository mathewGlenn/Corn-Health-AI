package com.glennappdev.cornhealthai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.databinding.ActivityPredictionResultBinding

class PredictionResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPredictionResultBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        val intent = getIntent()

        val prediction = intent.getStringExtra("pred").toString()

        binding.img.setImageBitmap(Constants.Image)
        binding.txtDname.text = prediction

        binding.cardResultD.setOnClickListener{
            startActivity(Intent(this, PredictionInfo::class.java).putExtra("pred", prediction))
        }
    }
}