package com.glennappdev.cornhealthai.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.glennappdev.cornhealthai.databinding.ActivityViewCornPartsBinding

class ViewCornParts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = ActivityViewCornPartsBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}