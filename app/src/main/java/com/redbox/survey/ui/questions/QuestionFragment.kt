package com.redbox.survey.ui.questions

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.redbox.survey.R
import com.redbox.survey.ui.results.ResultsFragment
import kotlinx.android.synthetic.main.fragment_question.*
import java.io.InputStream

class QuestionFragment(private val topic: String) : Fragment() {

    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.questionNum.observe(viewLifecycleOwner, Observer {
            if (it < 10) {
                progress_tv.text = "Вопрос ${it + 1} из 10"
            } else progress_tv.text = "Вопрос ${it} из 10"
        })

        var answerButtons = mutableListOf<Button>(
            question_first_btn,
            question_second_btn,
            question_third_btn,
            question_fourth_btn
        )

        viewModel.questionState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is QuestionState.Initial -> {
                    viewModel.getNext()
                }
                is QuestionState.Loading -> {
                    loadImage(it.image)
                    for (i in 0 until answerButtons.size) {
                        answerButtons[i].text = it.answers[i]
                    }
                }
                is QuestionState.Correct -> {
                    result_tv.text = "Верно"
                    result_tv.setBackgroundColor(
                        ContextCompat.getColor(
                            activity!!.applicationContext,
                            R.color.colorCorrect
                        )
                    )
                    result_tv.visibility = View.VISIBLE
                    Handler().postDelayed({
                        result_tv.visibility = View.INVISIBLE
                        viewModel.getNext()
                    }, 500)
                }
                is QuestionState.Incorrect -> {
                    result_tv.text = "Неверно"
                    result_tv.setBackgroundColor(
                        ContextCompat.getColor(
                            activity!!.applicationContext,
                            R.color.colorIncorrect
                        )
                    )
                    result_tv.visibility = View.VISIBLE
                    Handler().postDelayed({
                        viewModel.getNext()
                        result_tv.visibility = View.INVISIBLE
                    }, 500)
                }
                is QuestionState.Finish -> {
                    for (i in answerButtons) {
                        i.isEnabled = false
                    }
                    question_skip_btn.isEnabled = false

                    val bundle = Bundle()
                    bundle.putParcelable("results", viewModel.test.value)

                    var frag = ResultsFragment(topic)

                    frag.arguments = bundle
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, frag)
                        .addToBackStack(null).commit()
                }
            }
        })

        question_first_btn.setOnClickListener {
            checkAnswer(question_first_btn.text.toString())
        }
        question_second_btn.setOnClickListener {
            checkAnswer(question_second_btn.text.toString())
        }
        question_third_btn.setOnClickListener {
            checkAnswer(question_third_btn.text.toString())
        }
        question_fourth_btn.setOnClickListener {
            checkAnswer(question_fourth_btn.text.toString())
        }

        question_skip_btn.setOnClickListener {
            viewModel.check("")
            viewModel.getNext()
        }
    }

    private fun checkAnswer(answer: String) {
        question_first_btn.isEnabled = false
        question_second_btn.isEnabled = false
        question_third_btn.isEnabled = false
        question_fourth_btn.isEnabled = false
        viewModel.check(answer)
    }

    private fun loadImage(image: String) {
        val stream: InputStream = activity?.assets!!.open("$topic/$image")
        val d = Drawable.createFromStream(stream, null)
        question_first_btn.isEnabled = true
        question_second_btn.isEnabled = true
        question_third_btn.isEnabled = true
        question_fourth_btn.isEnabled = true
        question_sign_iv.setImageDrawable(d)
        stream.close()
    }
}