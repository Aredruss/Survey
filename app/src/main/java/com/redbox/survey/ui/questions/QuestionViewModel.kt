package com.redbox.survey.ui.questions

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.survey.domain.QuestionRepository
import com.redbox.survey.domain.Test

class QuestionViewModel : ViewModel() {

    val questionNum = MutableLiveData<Int>()
    val questionState = MutableLiveData<QuestionState>()
    val test = MutableLiveData<Test>()

    init {
        test.value = Test(
            0,
            0,
            QuestionRepository.generateTest(),
            ArrayList<Int>(),
            ArrayList<String>()
        )
        questionNum.value = -1
        questionState.value = QuestionState.Initial()
    }

    fun getNext() {
        questionNum.value = questionNum.value!! + 1
        if (questionNum.value!! < test.value!!.lineUp.size) {
            val image = test.value!!.lineUp[questionNum.value!!].picture
            val answers = QuestionRepository.generateAnswers(image)
            questionState.postValue(QuestionState.Loading(image, answers))
        } else {
            finish()
        }
    }

    fun check(mAnswer: String) {
        test.value!!.answers.add(mAnswer)
        if (test.value!!.lineUp[questionNum.value!!].answer.equals(mAnswer.toLowerCase())) {
            questionState.postValue(QuestionState.Correct)
            test.value!!.correct++
        } else {
            questionState.postValue(QuestionState.Incorrect(test.value!!.lineUp[questionNum.value!!].answer))
            test.value!!.mistakes.add(questionNum.value!!)
            test.value!!.incorrect++
        }
    }

    private fun finish() {
        questionState.postValue(QuestionState.Finish)
        Log.e("Results", test.value.toString())
    }
}