package com.redbox.survey.presentation.questions

import androidx.lifecycle.MutableLiveData
import com.redbox.survey.domain.Question

data class Test(
    val correct: Int,
    val incorrect: Int,
    val lineUp: ArrayList<Question>,
    val mistakes: ArrayList<Int>
)