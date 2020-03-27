package com.redbox.survey.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(val picture: String, val answer: String) : Parcelable