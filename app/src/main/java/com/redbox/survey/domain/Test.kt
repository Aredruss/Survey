package com.redbox.survey.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Test(
    var correct: Int,
    var incorrect: Int,
    val lineUp: ArrayList<Question>,
    val mistakes: ArrayList<Int>,
    val answers: ArrayList<String>
) : Parcelable