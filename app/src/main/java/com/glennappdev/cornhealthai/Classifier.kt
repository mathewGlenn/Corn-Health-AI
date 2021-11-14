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

    data class SecondMax(var argMax2: Int, var maxProbability2: Float)

    fun secondHighest(inputs: MutableList<Float>): SecondMax {

        val sorted = inputs
        sorted.sortDescending()
        val secHighest = sorted[1]
        val secHighestIndex = inputs.indexOf(secHighest)
        return SecondMax(secHighestIndex, secHighest)
    }

    data class Prediction(
        var predClass1: String,
        var class1Prob: Float,
        var predClass2: String,
        var class2Prob: Float
    )

    fun predict(bitmap: Bitmap?, model: String?): Prediction {
        val tensor = preprocess(bitmap, 224)
        val inputs = IValue.from(tensor)
        val outputs = this.model.forward(inputs).toTensor()
        val scores = outputs.dataAsFloatArray
        val (class1Index, maxValue) = argMax(softMax(scores))
        val (class2Index, secondMaxValue) = secondHighest(softMax(scores))

        var predictedClass1 = ""
        val class1Prob = maxValue * 100

        var predictedClass2 = ""
        val class2Prob = secondMaxValue * 100

        if (model == "insect_pest") {
            predictedClass1 = Constants.INSECT_PEST[class1Index]
            predictedClass2 = Constants.INSECT_PEST[class2Index]
        } else if (model == "leaf_disease") {
            predictedClass1 = Constants.LEAF_DISEASES[class1Index]
            predictedClass2 = Constants.LEAF_DISEASES[class2Index]
        }
        return Prediction(predictedClass1, class1Prob, predictedClass2, class2Prob)
    }

}