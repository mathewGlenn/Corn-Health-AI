package com.glennappdev.cornhealthai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val closeButton : CardView = findViewById(R.id.btnBack)

        closeButton.setOnClickListener{
            finish()
        }
    }
}