package com.glennappdev.cornhealthai

import android.graphics.Bitmap

class Constants {
    companion object{
        var Image : Bitmap? = null

        val LEAF_DISEASES = arrayListOf(
            "Common Rust",
            "Gray Leaf Spot",
            "Northern Leaf Blight"
        )

        val INSECT_PEST = arrayListOf(
            "Army Worm",
            "Corn Aphid",
            "Corn Borer",
            "Flea Beetle",
            "White Grub",
            "Wire Worm"
        )
    }
}