package com.glennappdev.cornhealthai

import android.graphics.Bitmap

class Constants {
    companion object{
        var Image : Bitmap? = null

        val LEAF_DISEASES = arrayListOf(
            "Common rust",
            "Gray leaf spot",
            "Northern leaf blight"
        )

        val LEAF_DISEASES_SCIENTIFIC_NAMES = arrayListOf(
            "Puccinia sorghi",
            "Cercospora zeae-maydis",
            "Setosphaeria turcica"
        )

        val INSECT_PEST = arrayListOf(
            "Army worm",
            "Corn aphid",
            "Corn borer",
            "Flea beetle",
            "White grub",
            "Wire worm"
        )
        val INSECT_PEST_SCIENTIFIC_NAMES = arrayListOf(
            "Spodoptera frugiperda",
            "Rhopalosiphum maidis",
            "Ostrinia nubilalis",
            "Chaetocnema pulicaria",
            "Holotrichia serrata",
            "Melanotus communis Gyllenhal"
        )
    }
}