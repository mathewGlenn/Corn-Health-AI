package com.glennappdev.cornhealthai.prediction

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.util.Linkify
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.settings.LocaleHelper
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityLeafDiseaseInfoBinding
import com.synnapps.carouselview.ImageListener


class LeafDiseaseInfo : AppCompatActivity() {

    var images = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLeafDiseaseInfoBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // set opacity of cardBar to 0
        val topBar = binding.cardBar
        val textBar = binding.txtBar
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

        // checked saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        val intent = intent
        val prediction = intent.getStringExtra("pred")

        binding.label.text = prediction

        binding.tDesc.text = resources.getString(R.string.description)
        binding.tSymptoms.text = resources.getString(R.string.symptoms)
        binding.tMgmtStrat.text = resources.getString(R.string.mgmt_strat)
        val chem_control: String

        when (prediction) {
            "Common rust" -> {
                binding.label.text = resources.getString(R.string.name_cr)
                binding.cause.text = resources.getString(R.string.cause_cr)
                binding.desc.text = resources.getString(R.string.desc_cr)
                binding.symptoms.text = resources.getString(R.string.symptoms_cr)
                binding.mgmt.text = resources.getString(R.string.mgmt_cr)
                chem_control =
                    resources.getString(R.string.chem_control_cr) + "\n\n" + resources.getString(R.string.note_when_using_fungicide)
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
                chem_control =
                    resources.getString(R.string.chem_control_gls) + "\n\n" + resources.getString(R.string.note_when_using_fungicide)
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
                chem_control =
                    resources.getString(R.string.chem_control_nlb) + "\n\n" + resources.getString(R.string.note_when_using_fungicide)
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

        carouselView.setImageClickListener { position ->
            val i = Intent(this, ViewImagesFull::class.java)
            i.putExtra("img", images[position])
            startActivity(i)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        val dialogText = SpannableString(
            resources.getString(R.string.fungicide_dialog) + " " +
                    resources.getString(R.string.fpa_link) + "\n\n")

        Linkify.addLinks(dialogText, Linkify.ALL)
        alertDialogBuilder.setTitle("Information")
        alertDialogBuilder.setMessage(dialogText)
        alertDialogBuilder.setCancelable(true)
            .setPositiveButton("Ok") { dialogInterface, i ->
                dialogInterface.cancel()
            }

        val alertDialog = alertDialogBuilder.create()
        binding.infoChemControl.setOnClickListener {
            alertDialog.show()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    var imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(images[position])
    }

}