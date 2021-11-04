package com.glennappdev.cornhealthai

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.glennappdev.cornhealthai.databinding.ActivityPredictBinding

class Predict : AppCompatActivity() {

    private lateinit var bitmap: Bitmap
    private lateinit var imgview: ImageView
    private lateinit var classifier: Classifier
    lateinit var flag: String

    private fun checkandGetPermissions() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 100)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPredictBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        checkandGetPermissions()

        val utils = Utils()
        val intent = intent
        flag = intent.getStringExtra("flag").toString()

        if (flag == "leaf_disease") {
            binding.txtHeadline.text = ("Classify Leaf Disease")
            classifier = Classifier(utils.assetFilePath(this, "ghostnet_leaf_diseases.pt+"))
        }else{
            binding.txtHeadline.text = ("Classify Insect Pest")
            classifier = Classifier(utils.assetFilePath(this, "mixnet_insect_pest.pt"))
        }


        imgview = findViewById(R.id.img)

        binding.cardChooseImg.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            startActivityForResult(i, 250)
        }
        binding.cardOpenCam.setOnClickListener {
            val cam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cam, 200)
        }
        binding.cardPredict.setOnClickListener {
            val prediction: String = classifier.predict(bitmap, flag)
            startActivity(Intent(this, PredictionResult::class.java).putExtra("pred", prediction))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 250) {
            imgview.setImageURI(data?.data)
            val uri: Uri? = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            Constants.Image = bitmap
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            //
            var yuv: Bitmap = data?.extras?.get("data") as Bitmap
            bitmap = yuv.copy(Bitmap.Config.ARGB_8888, true)
            imgview.setImageBitmap(bitmap)
            Constants.Image = bitmap
        }


    }

}