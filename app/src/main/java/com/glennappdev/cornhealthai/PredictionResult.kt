package com.glennappdev.cornhealthai

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.databinding.ActivityPredictionResultBinding

class PredictionResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityPredictionResultBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val intent = intent

        val model = intent.getStringExtra("model")

        val class1 = intent.getStringExtra("class1Name").toString()
        val class1Prob = intent.getFloatExtra("class1Prob", 0f)
        val class2 = intent.getStringExtra("class2Name").toString()
        val class2Prob = intent.getFloatExtra("class2Prob", 0f)

        binding.img.setImageBitmap(Constants.Image)

        binding.txtClassName.text = class1

        val score =
            if (class1Prob == 100f)
                "100%"
            else
                "%.2f".format(class1Prob) + "%"

        binding.txtScore.text = score

        when (class1) {
            // leaf diseases
            "Common rust" -> {
                binding.imgSmall.setImageResource(R.drawable.cr1)
            }
            "Gray leaf spot" -> {
                binding.imgSmall.setImageResource(R.drawable.gls1)
            }
            "Northern leaf blight" -> {
                binding.imgSmall.setImageResource(R.drawable.nlb1)
            }

            // insect pests
            "Army worm" -> {
                binding.imgSmall.setImageResource(R.drawable.aw1)
            }
            "Corn aphid" -> {
                binding.imgSmall.setImageResource(R.drawable.ca1)
            }
            "Corn borer" -> {
                binding.imgSmall.setImageResource(R.drawable.cb1)
            }
            "Flea beetle" -> {
                binding.imgSmall.setImageResource(R.drawable.fb1)
            }
            "White grub" -> {
                binding.imgSmall.setImageResource(R.drawable.wg1)
            }
            "Wire worm" -> {
                binding.imgSmall.setImageResource(R.drawable.ww1)
            }
        }

        if (class1Prob <= 90) {
            binding.cardResult2.visibility = View.VISIBLE
            binding.txtClassName2.text = class2
            val score2 = "%.2f".format(class2Prob) + "%"
            binding.txtScore2.text = score2
        }
        when (class2) {
            // leaf diseases
            "Common rust" -> {
                binding.imgSmall2.setImageResource(R.drawable.cr1)
            }
            "Gray leaf spot" -> {
                binding.imgSmall2.setImageResource(R.drawable.gls1)
            }
            "Northern leaf blight" -> {
                binding.imgSmall2.setImageResource(R.drawable.nlb1)
            }

            // insect pests
            "Army worm" -> {
                binding.imgSmall2.setImageResource(R.drawable.aw1)
            }
            "Corn aphid" -> {
                binding.imgSmall2.setImageResource(R.drawable.ca1)
            }
            "Corn borer" -> {
                binding.imgSmall2.setImageResource(R.drawable.cb1)
            }
            "Flea beetle" -> {
                binding.imgSmall2.setImageResource(R.drawable.fb1)
            }
            "White grub" -> {
                binding.imgSmall2.setImageResource(R.drawable.wg1)
            }
            "Wire worm" -> {
                binding.imgSmall2.setImageResource(R.drawable.ww1)
            }
        }

        binding.cardResultD.setOnClickListener {
            if (model == "leaf_disease") {
                val i = Intent(this, LeafDiseaseInfo::class.java)
                i.putExtra("pred", class1)
                startActivity(i)

            } else
                startActivity(Intent(this, InsectPestInfo::class.java).putExtra("pred", class1))
        }

        binding.cardResult2.setOnClickListener {
            if (model == "leaf_disease")
                startActivity(Intent(this, LeafDiseaseInfo::class.java).putExtra("pred", class2))
            else
                startActivity(Intent(this, InsectPestInfo::class.java).putExtra("pred", class2))
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        var dialogMessage = ""

        if (model == "leaf_disease")
            dialogMessage = "Please check if the predicted disease match the damage in your crop."
        else
            dialogMessage =
                "Please check if the predicted insect pest match the description of the insect pest found in your crop."

        alertDialogBuilder.setMessage(dialogMessage)
        alertDialogBuilder.setCancelable(true)
        val alertDialog = alertDialogBuilder.create()

        binding.info.setOnClickListener {
            alertDialog.show()
        }

        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}