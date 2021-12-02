package com.glennappdev.cornhealthai.prediction

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.glennappdev.cornhealthai.*
import com.glennappdev.cornhealthai.databinding.ActivityObtainImageBinding
import com.glennappdev.cornhealthai.settings.LocaleHelper
import com.jaeger.library.StatusBarUtil
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class ObtainImage : AppCompatActivity() {

    private lateinit var classifier: Classifier
    lateinit var mutableBitmap: Bitmap

    val APP_TAG = "crop"
    var intermediateName = "1.jpg"
    var resultName = "2.jpg"
    var intermediateProvider: Uri? = null
    var resultProvider: Uri? = null
    var cameraActivityResultLauncher: ActivityResultLauncher<Intent>? = null
    var galleryActivityResultLauncher: ActivityResultLauncher<Intent>? = null
    var cropActivityResultLauncher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityObtainImageBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        // check saved locale
        val preferences: SharedPreferences =
            this.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        val language = preferences.getString("SAVED_LANGUAGE", "en")
        val localeHelper = LocaleHelper()
        val context = localeHelper.setLocale(this, language.toString())
        val resources = context.resources


        binding.txtTips.text = resources.getString(R.string.tips_obtain_image)
        binding.tTooFar.text = resources.getString(R.string.tips_too_far)
        binding.tTooClose.text = resources.getString(R.string.tips_too_close)
        binding.tBlurredImage.text = resources.getString(R.string.tips_blurred_img)
        binding.tOpenCam.text = resources.getString(R.string.open_cam)
        binding.tOpenGallery.text = resources.getString(R.string.open_gallery)
        // transparent status bar using laobie/StatusBarUtil
        StatusBarUtil.setTransparent(this)


        val utils = Utils()
        val intent = intent
        val model = intent.getStringExtra("model").toString()

        if (model == "leaf_disease") {
            classifier = Classifier(utils.assetFilePath(this, "ghostnet_leaf_diseases.pt"))
        } else {
            classifier = Classifier(utils.assetFilePath(this, "mixnet_insect_pest.pt"))
            binding.tipsCorrect.setImageResource(R.drawable.tips_correct_insect)
            binding.tipsToFar.setImageResource(R.drawable.tips_too_far_insect)
            binding.tipsToClose.setImageResource(R.drawable.tips_concealed_insect)
            binding.tipsBlur.setImageResource(R.drawable.tips_blurred_insect)
            binding.parentLayout.setBackgroundResource(R.drawable.gradient_obtain_image_yellow)
            binding.tTooClose.text = resources.getString(R.string.concealed)
        }


        binding.openCamera.setOnClickListener {
            onLaunchCamera()
        }
        binding.openGallery.setOnClickListener {
            onPickPhoto()
        }

        cameraActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                // val takenImage = loadFromUri(intermediateProvider)
                // val bitmap = setImageBitmap(getResizedBitmap(takenImage, 400))
                onCropImage()
            }
        }
        galleryActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                saveBitmapFileToIntermediate(result.data!!.data)
                onCropImage()
            }
        }
        cropActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val cropImage = loadFromUri(resultProvider)
                val imgBitmap = getResizedBitmap(cropImage, 2000)
                //imgBitmap is immutable so I copy it to a mutable
                //bitmap so we can use the bitmap for image processing
                mutableBitmap = imgBitmap.copy(Bitmap.Config.ARGB_8888, true)
                Image.Image = mutableBitmap

                val (class1, class1Prob, class2, class2Prob) = classifier.predict(mutableBitmap,
                    model)
                val i = Intent(this, PredictionResult::class.java)
                i.putExtra("class1Name", class1)
                i.putExtra("class1Prob", class1Prob)
                i.putExtra("class2Name", class2)
                i.putExtra("class2Prob", class2Prob)
                i.putExtra("model", model)
                startActivity(i)
                finish()
            }
        }
    }

    fun onLaunchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile = getPhotoFileUri(intermediateName)
        intermediateProvider =
            FileProvider.getUriForFile(
                this,
                "com.photostream.crop.fileprovider",
                photoFile
            )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, intermediateProvider)
        if (intent.resolveActivity(packageManager) != null) {
            cameraActivityResultLauncher!!.launch(intent)
        }
    }

    // Trigger gallery selection for a photo
    fun onPickPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(packageManager) != null) {
            galleryActivityResultLauncher!!.launch(intent)
        }
    }

    private fun onCropImage() {
        grantUriPermission(
            "com.android.camera",
            intermediateProvider,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(intermediateProvider, "image/*")

        val list = packageManager.queryIntentActivities(intent, 0)

        grantUriPermission(
            list[0].activityInfo.packageName,
            intermediateProvider,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION

        )

        val size: Int = list.size
        if (size == 0) {
            Toast.makeText(this, "Error: No image taken!", Toast.LENGTH_SHORT).show()
        } else {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("scale", true);
            val photoFile = getPhotoFileUri(resultName)
            // wrap File object into a content provider
            // required for API >= 24
            // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
            resultProvider = FileProvider.getUriForFile(
                this,
                "com.photostream.crop.fileprovider",
                photoFile
            )
            intent.putExtra("return-data", false)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, resultProvider)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            val cropIntent = Intent(intent)
            val res = list[0]
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            cropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            grantUriPermission(
                res.activityInfo.packageName,
                resultProvider,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            cropIntent.component =
                ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            cropActivityResultLauncher!!.launch(cropIntent)
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    fun getPhotoFileUri(fileName: String): File {
        val mediaStorageDir = File(getExternalFilesDir(""), APP_TAG)
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(APP_TAG, "failed to create directory")
        }
        return File(mediaStorageDir.path + File.separator + fileName)
    }

    fun loadFromUri(photoUri: Uri?): Bitmap? {
        var image: Bitmap? = null
        try {
            image = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
                // on newer versions of Android, use the new decodeBitmap method
                val source = ImageDecoder.createSource(
                    this.contentResolver,
                    photoUri!!
                )
                ImageDecoder.decodeBitmap(source)
            } else {
                // support older versions of Android by using getBitmap
                MediaStore.Images.Media.getBitmap(this.contentResolver, photoUri)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }

    private fun saveBitmapFileToIntermediate(sourceUri: Uri?) {
        try {
            val bitmap = loadFromUri(sourceUri)
            val imageFile = getPhotoFileUri(intermediateName)
            intermediateProvider =
                FileProvider.getUriForFile(
                    this,
                    "com.photostream.crop.fileprovider",
                    imageFile
                )
            val out: OutputStream = FileOutputStream(imageFile)
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getResizedBitmap(image: Bitmap?, maxSize: Int): Bitmap {
        var width = image!!.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    fun cancel(view: android.view.View) {
        finish()
    }
}