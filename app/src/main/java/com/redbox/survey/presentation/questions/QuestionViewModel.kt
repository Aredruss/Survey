package com.redbox.survey.presentation.questions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.survey.domain.QuestionRepository

class QuestionViewModel() : ViewModel() {

    val questionNum = MutableLiveData<Int>()
    val questionState = MutableLiveData<QuestionState>()
    val test = MutableLiveData<Test>()

    init {
        questionState.value = QuestionState.Initial
        test.value = Test(0, 0, QuestionRepository.generateTest(), ArrayList<Int>())
        questionNum.value = 0
    }

    fun getNext(){
        if(questionNum.value!! < test.value!!.lineUp.size){
            questionNum.postValue(questionNum!!.value!! + 1)
            questionState.postValue(QuestionState.Loading(test!!.value!!.lineUp[questionNum.value!!].picture))
        }else {
            finish()
        }
    }

    fun check(mAnswer : String){
        if(test.value!!.lineUp[questionNum.value!!].answer.equals(mAnswer.toLowerCase())){
            questionState.postValue(QuestionState.Correct)
        } else{
            questionState.postValue(QuestionState.Incorrect(test.value!!.lineUp[questionNum.value!!].answer))
        }
    }

    fun finish(){
        questionState.postValue(QuestionState.Finish)
    }
}