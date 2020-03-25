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
        testQuestions.shuffle()
        return testQuestions
    }

    fun generateAnswers(firstAn: String): ArrayList<String> {
        val answers = ArrayList<String>()
        val imgs = ArrayList<String>()
        imgs.addAll(images)

        imgs.remove(firstAn)

        val f = firstAn.removeSuffix(".png")

        for (i in 1..3) {
            val answer = imgs.random()
            imgs.remove(answer)

            val a = answer.removeSuffix(".png")
            answers.add(a)
        }

        answers.add(f)
        answers.shuffle()
        return answers
    }
}