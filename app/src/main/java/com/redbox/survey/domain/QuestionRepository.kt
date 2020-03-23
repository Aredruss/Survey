package com.redbox.survey.domain

import kotlin.collections.ArrayList

object QuestionRepository {

    lateinit var images: List<String>

    fun setTest(ims: List<String>) {
        images = ims.filter { it.contains(".png") }
    }

    fun generateTest(): ArrayList<Question> {
        val testQuestions = ArrayList<Question>()
        images.forEach {
            testQuestions.add(Question(it, it.removeSuffix(".png").toLowerCase()))
        }
        return testQuestions
    }
}