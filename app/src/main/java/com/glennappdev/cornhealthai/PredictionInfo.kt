package com.glennappdev.cornhealthai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.glennappdev.cornhealthai.databinding.ActivityPredictionInfoBinding

class PredictionInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPredictionInfoBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        val intent = getIntent()
        val prediction = intent.getStringExtra("pred").toString()

        binding.label.text = prediction
        binding.img.setImageBitmap(Constants.Image)

        when (prediction) {
            "Common Rust" -> {
                binding.cause.text = resources.getString(R.string.cause_cr)
                binding.facts.text = resources.getString(R.string.facts_cr)
                binding.symptoms.text = resources.getString(R.string.symptoms_cr)
                binding. mgmt.text = resources.getString(R.string.mgmt_cr)
            }
            "Gray Leaf Spot" -> {
                binding.cause.text = resources.getString(R.string.cause_gls)
                binding.facts.text = resources.getString(R.string.facts_gls)
                binding.symptoms.text = resources.getString(R.string.symptoms_gls)
                binding.mgmt.text = resources.getString(R.string.mgmt_gls)
            }
            "Northern Leaf Blight" -> {
                binding.cause.text = resources.getString(R.string.cause_nlb)
                binding.facts.text = resources.getString(R.string.facts_nlb)
                binding.symptoms.text = resources.getString(R.string.symptoms_nlb)
                binding.mgmt.text = resources.getString(R.string.mgmt_nlb)
            }
        }
    }
}