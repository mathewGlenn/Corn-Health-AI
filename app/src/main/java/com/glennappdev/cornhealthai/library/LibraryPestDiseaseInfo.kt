package com.glennappdev.cornhealthai.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.ViewImagesFull
import com.glennappdev.cornhealthai.databinding.ActivityLibPestDiseaseInfoBinding
import com.synnapps.carouselview.ImageListener

class LibraryPestDiseaseInfo : AppCompatActivity() {

    var images = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLibPestDiseaseInfoBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // set opacity of cardBar to 0
        val topBar = binding.cardBar
        val textBar = binding.topBarText
        topBar.alpha = 0f
        textBar.alpha = 0f
        // while scrolling up, opacity of cardBar increases up to original opacity
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            val maxDistance = binding.carouselView.height
            val movement = binding.scrollView.scrollY
            val alphaFactor: Float = ((movement * 1.0f) / (maxDistance - topBar.height))
            if (movement in 0..maxDistance) {
                topBar.alpha = alphaFactor
                textBar.alpha = alphaFactor
            }
        }

        val cardClicked = intent.getStringExtra("card")

        when (cardClicked) {
            "card_cr" -> {
                binding.topBarText.text = "Common Rust"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.cause_cr)
                binding.tSciCause.text = "cause"
                binding.desc.text = resources.getString(R.string.desc_cr)
                binding.damageSymptoms.text = resources.getString(R.string.symptoms_cr)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_cr)

                binding.tDamageSymptom.text = "Symptoms"
                binding.biologicalOrganicControl.text = "Organic control"
                // binding.biologicalOrganicControl.text =

                images = arrayListOf(
                    R.drawable.cr1,
                    R.drawable.cr2,
                    R.drawable.cr3,
                    R.drawable.cr4)
            }

            "card_gls" -> {
                binding.topBarText.text = "Gray Leaf Spot"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.cause_gls)
                binding.tSciCause.text = "cause"
                binding.desc.text = resources.getString(R.string.desc_gls)
                binding.damageSymptoms.text = resources.getString(R.string.symptoms_gls)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_gls)

                binding.tDamageSymptom.text = "Symptoms"
                binding.biologicalOrganicControl.text = "Organic control"
                // binding.biologicalOrganicControl.text =

                images = arrayListOf(
                    R.drawable.gls1,
                    R.drawable.gls2,
                    R.drawable.gls3,
                    R.drawable.gls4,
                    R.drawable.gls5)
            }

            "card_nlb" -> {
                binding.topBarText.text = "Northern Leaf Blight"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.cause_nlb)
                binding.tSciCause.text = "cause"
                binding.desc.text = resources.getString(R.string.desc_nlb)
                binding.damageSymptoms.text = resources.getString(R.string.symptoms_nlb)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_nlb)

                binding.tDamageSymptom.text = "Symptoms"
                binding.biologicalOrganicControl.text = "Organic control"
                // binding.biologicalOrganicControl.text =

                images = arrayListOf(
                    R.drawable.nlb1,
                    R.drawable.nlb2,
                    R.drawable.nlb3,
                    R.drawable.nlb4)
            }


            "card_aw" -> {
                binding.topBarText.text = "Army Worm"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.sci_name_aw)
                binding.desc.text = resources.getString(R.string.desc_aw)
                binding.damageSymptoms.text = resources.getString(R.string.damage_aw)
                binding.contentBiologicalOrganicControl.text =
                    resources.getString(R.string.natural_enemies_aw)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_aw)

                images = arrayListOf(
                    R.drawable.aw1,
                    R.drawable.aw2,
                    R.drawable.aw3,
                    R.drawable.aw4,
                    R.drawable.aw5,
                    R.drawable.aw6,
                    R.drawable.aw7,
                    R.drawable.aw8,
                    R.drawable.aw9)

            }
            "card_ca" -> {
                binding.topBarText.text = "Corn Aphid"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.sci_name_ca)
                binding.desc.text = resources.getString(R.string.desc_ca)
                binding.damageSymptoms.text = resources.getString(R.string.damage_ca)
                binding.contentBiologicalOrganicControl.text =
                    resources.getString(R.string.natural_enemies_ca)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_ca)

                images = arrayListOf(
                    R.drawable.ca1,
                    R.drawable.ca2,
                    R.drawable.ca3,
                    R.drawable.ca4,
                    R.drawable.ca5,
                    R.drawable.ca6,
                    R.drawable.ca7)
            }
            "card_cb" -> {
                binding.topBarText.text = "Corn Borer"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.sci_name_cb)
                binding.desc.text = resources.getString(R.string.desc_cb)
                binding.damageSymptoms.text = resources.getString(R.string.damage_cb)
                binding.contentBiologicalOrganicControl.text =
                    resources.getString(R.string.natural_enemies_cb)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_cb)

                images = arrayListOf(
                    R.drawable.cb1,
                    R.drawable.cb2,
                    R.drawable.cb3,
                    R.drawable.cb4,
                    R.drawable.cb5,
                    R.drawable.cb6)
            }
            "card_fb" -> {
                binding.topBarText.text = "Corn Flea Beetle"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.sci_name_fb)
                binding.desc.text = resources.getString(R.string.desc_fb)
                binding.damageSymptoms.text = resources.getString(R.string.damage_fb)
                binding.contentBiologicalOrganicControl.text =
                    resources.getString(R.string.natural_enemies_fb)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_fb)

                images = arrayListOf(
                    R.drawable.fb1,
                    R.drawable.fb2,
                    R.drawable.fb3,
                    R.drawable.fb4,
                    R.drawable.fb5,
                    R.drawable.fb6)
            }
            "card_wg" -> {
                binding.topBarText.text = "White Grub"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.sci_name_wg)
                binding.desc.text = resources.getString(R.string.desc_wg)
                binding.damageSymptoms.text = resources.getString(R.string.damage_wg)
                binding.contentBiologicalOrganicControl.text =
                    resources.getString(R.string.natural_enemies_wg)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_wg)

                images = arrayListOf(
                    R.drawable.wg1,
                    R.drawable.wg2,
                    R.drawable.wg3,
                    R.drawable.wg4,
                    R.drawable.wg5)
            }
            "card_ww" -> {
                binding.topBarText.text = "Wireworm"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.sci_name_ww)
                binding.desc.text = resources.getString(R.string.desc_ww)
                binding.damageSymptoms.text = resources.getString(R.string.damage_ww)
                binding.contentBiologicalOrganicControl.text =
                    resources.getString(R.string.natural_enemies_ww)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_ww)

                images = arrayListOf(
                    R.drawable.ww1,
                    R.drawable.ww2,
                    R.drawable.ww3,
                    R.drawable.ww4,
                    R.drawable.ww5,
                    R.drawable.ww6,
                    R.drawable.ww7,
                    R.drawable.ww8,
                    R.drawable.ww9)

            }
        }

        val carouselView = binding.carouselView
        carouselView.pageCount = images.size
        carouselView.setImageListener(imageListener)

        carouselView.setImageClickListener { position ->
            val i = Intent(this, ViewImagesFull::class.java)
            i.putExtra("img", images[position])
            startActivity(i)
        }

        binding.btnBack.setOnClickListener{
            finish()
        }
    }

    var imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(images[position])
    }
}