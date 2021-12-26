package com.glennappdev.cornhealthai.library

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityLibPestDiseaseInfoBinding
import com.glennappdev.cornhealthai.naturalenemies.EnemiesOfCa
import com.glennappdev.cornhealthai.naturalenemies.EnemiesOfCfb
import com.glennappdev.cornhealthai.naturalenemies.EnemiesOfFaw
import com.glennappdev.cornhealthai.naturalenemies.EnemiesOfWw
import com.glennappdev.cornhealthai.prediction.ViewImagesFull
import com.glennappdev.cornhealthai.settings.LocaleHelper
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

        //get intent
        val card = intent.getStringExtra("card")

        // checked saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.tDesc.text = resources.getString(R.string.description)
        binding.tPrevMeasures.text = resources.getString(R.string.preventive_measures)
        binding.tTreatment.text = resources.getString(R.string.treatment)

        binding.showNaturalEnemyImgs.setOnClickListener {
           when(card){
               "card_faw" ->{
                   startActivity(Intent(this, EnemiesOfFaw::class.java))
               }

               "card_ca" ->{
                   startActivity(Intent(this, EnemiesOfCa::class.java))
               }

               "card_cb" ->{
                   startActivity(Intent(this, EnemiesOfFaw::class.java))
               }

               "card_fb" ->{
                   startActivity(Intent(this, EnemiesOfCfb::class.java))
               }

               "card_ww" ->{
                   startActivity(Intent(this, EnemiesOfWw::class.java))
               }
           }
        }

        when (card) {
            "card_cr" -> {
                binding.biologicalOrganicControl.visibility = View.GONE
                binding.contentBiologicalOrganicControl.visibility = View.GONE
                binding.topBarText.text = "Common Rust"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.cause_cr)
                binding.tSciCause.text = "cause"
                binding.desc.text = resources.getString(R.string.desc_cr)
                binding.damageSymptoms.text = resources.getString(R.string.symptoms_cr)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_cr)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_cr)
                binding.tDamageSymptom.text = resources.getString(R.string.symptoms)
                binding.biologicalOrganicControl.text = "Organic control"
                // binding.biologicalOrganicControl.text =

                images = arrayListOf(
                    R.drawable.cr1,
                    R.drawable.cr2,
                    R.drawable.cr3,
                    R.drawable.cr4)

                binding.showNaturalEnemyImgs.visibility = View.GONE
            }

            "card_gls" -> {
                binding.biologicalOrganicControl.visibility = View.GONE
                binding.contentBiologicalOrganicControl.visibility = View.GONE

                binding.topBarText.text = "Gray Leaf Spot"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.cause_gls)
                binding.tSciCause.text = "cause"
                binding.desc.text = resources.getString(R.string.desc_gls)
                binding.damageSymptoms.text = resources.getString(R.string.symptoms_gls)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_gls)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_gls)
                binding.tDamageSymptom.text = resources.getString(R.string.symptoms)
                binding.biologicalOrganicControl.text = "Organic control"
                // binding.biologicalOrganicControl.text =

                images = arrayListOf(
                    R.drawable.gls1,
                    R.drawable.gls2,
                    R.drawable.gls3,
                    R.drawable.gls4,
                    R.drawable.gls5)

                binding.showNaturalEnemyImgs.visibility = View.GONE
            }

            "card_nlb" -> {
                binding.biologicalOrganicControl.visibility = View.GONE
                binding.contentBiologicalOrganicControl.visibility = View.GONE

                binding.topBarText.text = "Northern Leaf Blight"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.cause_nlb)
                binding.tSciCause.text = "cause"
                binding.desc.text = resources.getString(R.string.desc_nlb)
                binding.damageSymptoms.text = resources.getString(R.string.symptoms_nlb)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_nlb)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_nlb)
                binding.tDamageSymptom.text = resources.getString(R.string.symptoms)
                binding.biologicalOrganicControl.text = "Organic control"
                // binding.biologicalOrganicControl.text =

                images = arrayListOf(
                    R.drawable.nlb1,
                    R.drawable.nlb2,
                    R.drawable.nlb3,
                    R.drawable.nlb4)

                binding.showNaturalEnemyImgs.visibility = View.GONE
            }


            "card_faw" -> {
                binding.topBarText.text = "Fall armyworm"
                binding.label.text = binding.topBarText.text
                binding.sciCause.text = resources.getString(R.string.sci_name_aw)
                binding.desc.text = resources.getString(R.string.desc_aw)
                binding.damageSymptoms.text = resources.getString(R.string.damage_aw)
                binding.contentBiologicalOrganicControl.text =
                    resources.getString(R.string.natural_enemies_aw)
                binding.chemicalControl.text = resources.getString(R.string.chem_control_aw)
                binding.tDamageSymptom.text = resources.getString(R.string.damage)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_faw)
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
                binding.tDamageSymptom.text = resources.getString(R.string.damage)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_ca)
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
                binding.tDamageSymptom.text = resources.getString(R.string.damage)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_cb)
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
                binding.tDamageSymptom.text = resources.getString(R.string.damage)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_fb)
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
                binding.tDamageSymptom.text = resources.getString(R.string.damage)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_wg)
                images = arrayListOf(
                    R.drawable.wg1,
                    R.drawable.wg2,
                    R.drawable.wg3,
                    R.drawable.wg4,
                    R.drawable.wg5)

                binding.showNaturalEnemyImgs.visibility = View.GONE
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
                binding.tDamageSymptom.text = resources.getString(R.string.damage)
                binding.preventiveMeasures.text = resources.getString(R.string.prevent_ww)
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

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    var imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(images[position])
    }
}