package com.glennappdev.cornhealthai

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glennappdev.cornhealthai.onboarding.OnBoarding

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref: SharedPreferences = this.getSharedPreferences("ONBOARDING", Context.MODE_PRIVATE)
        val isAppFirstTimeUse = pref.getBoolean("APP_FIRST_TIME_USE", true)

        if (isAppFirstTimeUse)
            startActivity(Intent(this, OnBoarding::class.java))
        else
            startActivity(Intent(this, MainActivity::class.java))

        finish()
    }
}