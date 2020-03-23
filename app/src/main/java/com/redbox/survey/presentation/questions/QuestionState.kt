package com.redbox.survey.presentation.questions

import com.redbox.survey.domain.Question

sealed class QuestionState {
    object Initial : QuestionState()
    object Correct : QuestionState()
    class Loading(val image: String) : QuestionState()
    class Incorrect(val answer: String) : QuestionState()
    object Finish : QuestionState()
}