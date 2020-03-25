package com.redbox.survey.presentation.questions
import com.redbox.survey.domain.Question

data class Test(
    var correct: Int,
    var incorrect: Int,
    val lineUp: ArrayList<Question>,
    val mistakes: ArrayList<Int>
)