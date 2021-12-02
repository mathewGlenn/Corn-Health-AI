package com.glennappdev.cornhealthai.library

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.R
import com.glennappdev.cornhealthai.databinding.ActivityLibraryBinding

import com.glennappdev.cornhealthai.settings.LocaleHelper

class Library : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLibraryBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // check saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources

        binding.txtLeafDiseases.text = resources.getString(R.string.leaf_diseases)
        binding.txtInsects.text = resources.getString(R.string.insect_pests)
        binding.txtMoreInfo.text = resources.getString(R.string.more_info)
        binding.txtViewCornParts.text = resources.getString(R.string.view_part_corn)

        // set opacity of cardBar to 0
        val topBar = binding.cardBar
        val textBar = binding.txtBar
        topBar.alpha = 0f
        textBar.alpha = 0f
        // while scrolling up, opacity of cardBar increases up to original opacity
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            val maxDistance = binding.imgTop.height
            val movement = binding.scrollView.scrollY
            val alphaFactor: Float = ((movement * 1.0f) / (maxDistance - topBar.height))
            if (movement in 0..maxDistance) {
                topBar.alpha = alphaFactor
                textBar.alpha = alphaFactor
            }
        }

        binding.cardCr.setOnClickListener(this)
        binding.cardGls.setOnClickListener(this)
        binding.cardNlb.setOnClickListener(this)
        binding.cardAw.setOnClickListener(this)
        binding.cardCa.setOnClickListener(this)
        binding.cardCb.setOnClickListener(this)
        binding.cardFb.setOnClickListener(this)
        binding.cardWg.setOnClickListener(this)
        binding.cardWw.setOnClickListener(this)
        binding.cardViewPartsCorn.setOnClickListener(this)
        binding.cardMonitorPest.setOnClickListener(this)
        binding.buttonMonitorPest.setOnClickListener(this)

        binding.txtBesideCornIcon.text = resources.getString(R.string.library_txt)
        binding.txtInfoFieldMonitoring.text = resources.getString(R.string.field_monitoring)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onClick(card: View?) {

        when (card?.id) {
            R.id.card_cr ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_cr"))

            R.id.card_gls ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_gls"))

            R.id.card_nlb ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_nlb"))

            R.id.card_aw ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_aw"))

            R.id.card_ca ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_ca"))

            R.id.card_cb ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_cb"))

            R.id.card_fb ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_fb"))

            R.id.card_wg ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_wg"))

            R.id.card_ww ->
                startActivity(Intent(this, LibraryPestDiseaseInfo::class.java)
                    .putExtra("card", "card_ww"))
            R.id.cardViewPartsCorn ->
                startActivity(Intent(this, ViewCornParts::class.java))
            R.id.cardMonitorPest ->
                startActivity(Intent(this, FieldMonitoring::class.java))
            R.id.buttonMonitorPest ->
                startActivity(Intent(this, FieldMonitoring::class.java))
        }
    }
}