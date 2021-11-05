package com.glennappdev.cornhealthai

import android.graphics.Bitmap
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils
import kotlin.math.pow

class Classifier(modelPath: String?) {

    var model: Module = Module.load(modelPath)
    var mean = floatArrayOf(0.485f, 0.456f, 0.406f)
    var std = floatArrayOf(0.229f, 0.224f, 0.225f)

    private val e = 2.71828f

    fun setMeanAndStd(mean: FloatArray, std: FloatArray) {
        this.mean = mean
        this.std = std
    }

    fun preprocess(bitmap: Bitmap?, size: Int): Tensor {
        var bitmap = bitmap
        bitmap = Bitmap.createScaledBitmap(bitmap!!, size, size, false)
        return TensorImageUtils.bitmapToFloat32Tensor(bitmap, mean, std)
    }

    fun softMax(inputs: FloatArray): MutableList<Float> {
        val softMaxProbs: MutableList<Float> = ArrayList()
        var sumOfProbs = 0.0f
        for (i in inputs.indices) {
            sumOfProbs += (e.pow(inputs[i]))
        }
        for (i in inputs.indices) {
            softMaxProbs.add(e.pow(inputs[i]) / sumOfProbs)
        }
        return softMaxProbs
    }

    data class Max(var argMax: Int, var maxProbability: Float)

    fun argMax(inputs: MutableList<Float>): Max {
        var maxIndex = -1
        var maxValue = 0.0f
        for (i in inputs.indices) {
            if (inputs[i] > maxValue) {
                maxIndex = i
                maxValue = inputs[i]
            }
        }
        return Max(maxIndex, maxValue)
    }

    data class Prediction(
        var predClass: String,
        var predSciName: String,
        var predProbability: String,
    )

    fun predict(bitmap: Bitmap?, model: String?): Prediction {
        val tensor = preprocess(bitmap, 224)
        val inputs = IValue.from(tensor)
        val outputs = this.model.forward(inputs).toTensor()
        val scores = outputs.dataAsFloatArray
        val (classIndex, maxValue) = argMax(softMax(scores))

        var predictedClass = ""
        var classSciName = ""
        val predictionProbability = "%.2f".format(maxValue * 100) + "%"

        if (model == "insect_pest") {
            predictedClass = Constants.INSECT_PEST[classIndex]
            classSciName = Constants.INSECT_PEST_SCIENTIFIC_NAMES[classIndex]
        } else if (model == "leaf_disease") {
            predictedClass = Constants.LEAF_DISEASES[classIndex]
            classSciName = Constants.LEAF_DISEASES_SCIENTIFIC_NAMES[classIndex]
        }
        return Prediction(predictedClass, classSciName, predictionProbability)
    }

}