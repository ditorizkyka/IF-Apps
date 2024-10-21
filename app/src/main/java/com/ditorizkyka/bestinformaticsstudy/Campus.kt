package com.ditorizkyka.bestinformaticsstudy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Campus(
    val name : String,
    val description : String,
    val image : Int,
    val location : String,
    val profile : String,

    val founded : String,
    val longDesc : String,
    val rector : String,
    val accreditation : String,

) : Parcelable
