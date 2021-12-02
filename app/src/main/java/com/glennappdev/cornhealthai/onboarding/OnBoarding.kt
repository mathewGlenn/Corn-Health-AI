package com.glennappdev.cornhealthai.onboarding


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.glennappdev.cornhealthai.MainActivity
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityOnBoardingBinding
import com.google.android.material.button.MaterialButton

class OnBoarding : AppCompatActivity() {

    lateinit var onBoardingAdapter: OnBoardingAdapter
    lateinit var layoutOnBoardingIndicator: LinearLayout
    lateinit var buttonOnboardingAction: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        layoutOnBoardingIndicator = binding.layoutOnboardingIndicators
        buttonOnboardingAction = binding.buttonOnBoardingAction

        setOnboardingItem()


        val onboardingViewPager = binding.onboardingViewPager
        onboardingViewPager.adapter = onBoardingAdapter

        setOnboardingIndicator()
        setCurrentOnboardingIndicators(0)

        onboardingViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicators(position)
            }
        })

        buttonOnboardingAction.setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onBoardingAdapter.itemCount) {
                onboardingViewPager.currentItem = onboardingViewPager.currentItem + 1
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // Only show Onboarding screen once, during the first use of the app.
        val pref: SharedPreferences = this.getSharedPreferences("ONBOARDING", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean("APP_FIRST_TIME_USE", false)
        editor.apply()


    }

    fun setOnboardingIndicator() {
        val indicators: Array<ImageView?> =
            arrayOfNulls(onBoardingAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            30,
            30
        )

        layoutParams.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.setImageDrawable(ContextCompat.getDrawable(
                applicationContext, R.drawable.onboarding_indicator_inactive
            ))
            indicators[i]!!.layoutParams = layoutParams
            layoutOnBoardingIndicator.addView(indicators[i])
        }
    }

    fun setCurrentOnboardingIndicators(index: Int) {
        val childCount: Int = layoutOnBoardingIndicator.childCount
        for (i in 0..childCount) {
            val imageView = layoutOnBoardingIndicator.getChildAt(i) as? ImageView
            if (i == index) {
                imageView?.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.onboarding_indicator_active
                ))
            } else {
                imageView?.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.onboarding_indicator_inactive
                ))
            }
        }

        if (index == onBoardingAdapter.itemCount - 1) {
            buttonOnboardingAction.text = "Get Started"
        } else {
            buttonOnboardingAction.text = "Next"
        }
    }


    fun setOnboardingItem() {
        val onBoardingItems: MutableList<OnBoardingItem> = ArrayList()

        val itemWelcome = OnBoardingItem()
        itemWelcome.title = "Welcome to \nCorn Health AI"
        itemWelcome.description = resources.getString(R.string.cont_welcome)
        itemWelcome.image = R.drawable.onboarding_logo

        val itemSlide2 = OnBoardingItem()
        itemSlide2.title = resources.getString(R.string.title_take_photo)
        itemSlide2.description = resources.getString(R.string.cont_take_photo)
        itemSlide2.image = R.drawable.capture_pest

        val itemSlide3 = OnBoardingItem()
        itemSlide3.title = resources.getString(R.string.title_instant_result)
        itemSlide3.description = resources.getString(R.string.cont_instant_result)
        itemSlide3.image = R.drawable.capture_result

        onBoardingItems.add(itemWelcome)
        onBoardingItems.add(itemSlide2)
        onBoardingItems.add(itemSlide3)

        onBoardingAdapter = OnBoardingAdapter(onBoardingItems)


    }
}