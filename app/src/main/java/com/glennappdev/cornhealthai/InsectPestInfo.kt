package com.glennappdev.cornhealthai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.databinding.ActivityInsectPestInfoBinding
import com.synnapps.carouselview.ImageListener

class InsectPestInfo : AppCompatActivity() {

    var images = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInsectPestInfoBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        val intent = intent
        val prediction = intent.getStringExtra("pred").toString()

        binding.label.text = prediction
       // binding.img.setImageBitmap(Constants.Image)

        val chem_control : String

        when (prediction) {
            "Army worm" -> {
                binding.label.text = resources.getString(R.string.name_aw)
                binding.sciName.text = resources.getString(R.string.sci_name_aw)
                binding.desc.text = resources.getString(R.string.desc_aw)
                binding.damage.text = resources.getString(R.string.damage_aw)
                binding.mgmt.text = resources.getString(R.string.mgmt_aw)
                binding.naturalEnemies.text = resources.getString(R.string.natural_enemies_aw)
                chem_control =  resources.getString(R.string.chem_control_aw) + "\n\n" + resources.getString(R.string.note_when_using_pesticide)
                binding.chemControl.text = chem_control
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
            "Corn aphid" -> {
                binding.label.text = resources.getString(R.string.name_ca)
                binding.sciName.text = resources.getString(R.string.sci_name_ca)
                binding.desc.text = resources.getString(R.string.desc_ca)
                binding.damage.text = resources.getString(R.string.damage_ca)
                binding.mgmt.text = resources.getString(R.string.mgmt_ca)
                binding.naturalEnemies.text = resources.getString(R.string.natural_enemies_ca)
                chem_control =  resources.getString(R.string.chem_control_ca) + "\n\n" + resources.getString(R.string.note_when_using_pesticide)
                binding.chemControl.text = chem_control
                images = arrayListOf(
                    R.drawable.ca1,
                    R.drawable.ca2,
                    R.drawable.ca3,
                    R.drawable.ca4,
                    R.drawable.ca5,
                    R.drawable.ca6,
                    R.drawable.ca7)
            }
            "Corn borer" -> {
                binding.label.text = resources.getString(R.string.name_cb)
                binding.sciName.text = resources.getString(R.string.sci_name_cb)
                binding.desc.text = resources.getString(R.string.desc_cb)
                binding.damage.text = resources.getString(R.string.damage_cb)
                binding.mgmt.text = resources.getString(R.string.mgmt_cb)
                binding.naturalEnemies.text = resources.getString(R.string.natural_enemies_cb)
                chem_control =  resources.getString(R.string.chem_control_cb) + "\n\n" + resources.getString(R.string.note_when_using_pesticide)
                binding.chemControl.text = chem_control
                images = arrayListOf(
                    R.drawable.cb1,
                    R.drawable.cb2,
                    R.drawable.cb3,
                    R.drawable.cb4,
                    R.drawable.cb5,
                    R.drawable.cb6)
            }
            "Flea beetle" -> {
                binding.label.text = resources.getString(R.string.name_fb)
                binding.sciName.text = resources.getString(R.string.sci_name_fb)
                binding.desc.text = resources.getString(R.string.desc_fb)
                binding.damage.text = resources.getString(R.string.damage_fb)
                binding.mgmt.text = resources.getString(R.string.mgmt_fb)
                binding.naturalEnemies.text = resources.getString(R.string.natural_enemies_fb)
                chem_control =  resources.getString(R.string.chem_control_fb) + "\n\n" + resources.getString(R.string.note_when_using_pesticide)
                binding.chemControl.text = chem_control
                images = arrayListOf(
                    R.drawable.fb1,
                    R.drawable.fb2,
                    R.drawable.fb3,
                    R.drawable.fb4,
                    R.drawable.fb5,
                    R.drawable.fb6)
            }
            "White grub" -> {
                binding.label.text = resources.getString(R.string.name_wg)
                binding.sciName.text = resources.getString(R.string.sci_name_wg)
                binding.desc.text = resources.getString(R.string.desc_wg)
                binding.damage.text = resources.getString(R.string.damage_wg)
                binding.mgmt.text = resources.getString(R.string.mgmt_wg)
                binding.naturalEnemies.text = resources.getString(R.string.natural_enemies_wg)
                chem_control =  resources.getString(R.string.chem_control_wg) + "\n\n" + resources.getString(R.string.note_when_using_pesticide)
                binding.chemControl.text = chem_control
                images = arrayListOf(
                    R.drawable.wg1,
                    R.drawable.wg2,
                    R.drawable.wg3,
                    R.drawable.wg4,
                    R.drawable.wg5)
            }
            "Wire worm" -> {
                binding.label.text = resources.getString(R.string.name_ww)
                binding.sciName.text = resources.getString(R.string.sci_name_ww)
                binding.desc.text = resources.getString(R.string.desc_ww)
                binding.damage.text = resources.getString(R.string.damage_ww)
                binding.mgmt.text = resources.getString(R.string.mgmt_ww)
                binding.naturalEnemies.text = resources.getString(R.string.natural_enemies_ww)
                chem_control =  resources.getString(R.string.chem_control_ww) + "\n\n" + resources.getString(R.string.note_when_using_pesticide)
                binding.chemControl.text = chem_control
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
    }

    var imageListener = ImageListener{
        position, imageView ->
        imageView.setImageResource(images[position])
    }
}