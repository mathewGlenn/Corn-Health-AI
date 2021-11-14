package com.glennappdev.cornhealthai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.databinding.ActivityLeafDiseaseInfoBinding
import com.synnapps.carouselview.ImageListener

class LeafDiseaseInfo : AppCompatActivity() {

    var images = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLeafDiseaseInfoBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val intent = intent
        val prediction = intent.getStringExtra("pred").toString()

        binding.label.text = prediction

        val chem_control : String

        when (prediction) {
            "Common rust" -> {
                binding.label.text = resources.getString(R.string.name_cr)
                binding.cause.text = resources.getString(R.string.cause_cr)
                binding.desc.text = resources.getString(R.string.desc_cr)
                binding.symptoms.text = resources.getString(R.string.symptoms_cr)
                binding.mgmt.text = resources.getString(R.string.mgmt_cr)
                chem_control = resources.getString(R.string.chem_control_cr) + "\n\n" + resources.getString(R.string.note_when_using_fungicide)
                binding.chemControl.text = chem_control
                images = arrayListOf(
                    R.drawable.cr1,
                    R.drawable.cr2,
                    R.drawable.cr3,
                    R.drawable.cr4)
            }
            "Gray leaf spot" -> {
                binding.label.text = resources.getString(R.string.name_gls)
                binding.cause.text = resources.getString(R.string.cause_gls)
                binding.desc.text = resources.getString(R.string.desc_gls)
                binding.symptoms.text = resources.getString(R.string.symptoms_gls)
                binding.mgmt.text = resources.getString(R.string.mgmt_gls)
                chem_control = resources.getString(R.string.chem_control_gls) + "\n\n" + resources.getString(R.string.note_when_using_fungicide)
                binding.chemControl.text = chem_control
                images = arrayListOf(
                    R.drawable.gls1,
                    R.drawable.gls2,
                    R.drawable.gls3,
                    R.drawable.gls4,
                    R.drawable.gls5)
            }
            "Northern leaf blight" -> {
                binding.label.text = resources.getString(R.string.name_nlb)
                binding.cause.text = resources.getString(R.string.cause_nlb)
                binding.desc.text = resources.getString(R.string.desc_nlb)
                binding.symptoms.text = resources.getString(R.string.symptoms_nlb)
                binding.mgmt.text = resources.getString(R.string.mgmt_nlb)
                chem_control = resources.getString(R.string.chem_control_nlb) + "\n\n" + resources.getString(R.string.note_when_using_fungicide)
                binding.chemControl.text = chem_control
                images = arrayListOf(
                    R.drawable.nlb1,
                    R.drawable.nlb2,
                    R.drawable.nlb3,
                    R.drawable.nlb4)
            }
        }
        val carouselView = binding.carouselView
        carouselView.pageCount = images.size
        carouselView.setImageListener(imageListener)
    }
    var imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(images[position])
    }
}