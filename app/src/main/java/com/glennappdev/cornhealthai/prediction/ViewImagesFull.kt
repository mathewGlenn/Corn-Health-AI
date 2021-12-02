package com.glennappdev.cornhealthai.prediction

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityViewImagesFullBinding


class ViewImagesFull : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewImagesFullBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val intent = intent
        val image = intent.getIntExtra("img", R.drawable.ww1)

        binding.image.setImageResource(image)
        binding.close.setOnClickListener {
            finish()
        }
    }
}