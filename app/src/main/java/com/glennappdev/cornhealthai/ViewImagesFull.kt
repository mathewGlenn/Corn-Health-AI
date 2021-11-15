package com.glennappdev.cornhealthai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.glennappdev.cornhealthai.databinding.ActivityViewImagesFullBinding


class ViewImagesFull : AppCompatActivity() {
    val images = Constants.ArrayImages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewImagesFullBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val intent = intent
        val image = intent.getIntExtra("img", R.drawable.ww1)

        binding.image.setImageResource(image)

    }
}