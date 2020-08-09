package com.redbox.survey.presentation.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.survey.R
import com.redbox.survey.domain.TestTopicsRepository
import com.redbox.survey.presentation.home.HomeFragment
import com.redbox.survey.presentation.questions.QuestionFragment
import kotlinx.android.synthetic.main.fragment_results.*

class ResultsFragment(private val topic: String) : Fragment() {

    private lateinit var viewModel: ResultsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultsState.Initial -> {
                    viewModel.setResults(arguments!!.getParcelable("results"))
                }
                is ResultsState.Loaded -> {
                    results_score_tv.text = "${it.test.correct}/10"
                    results_mistakes_rv.layoutManager = LinearLayoutManager(this.context)
                    results_mistakes_rv.adapter = MistakesListAdapter(it.test, topic)

                    val testTheme = TestTopicsRepository.topics.find {
                        it.first == topic
                    }?.second
                    val resultEvent = "Пользователь ___ сдал тест по теме " +
                            "\"${activity?.getString(testTheme!!)}\" на ${it.test.correct}/10 баллов"

                    //Analytics.trackEvent(resultEvent)


                }
            }
        })

        results_unfold_ib.setOnClickListener {
            it.rotation += 180F
            if (results_mistakes_rv.visibility == View.GONE) {
                results_mistakes_rv.visibility = View.VISIBLE
            } else results_mistakes_rv.visibility = View.GONE
        }

        results_again_btn.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, QuestionFragment(topic)).addToBackStack(null).commit()
        }

        results_end_btn.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, HomeFragment()).addToBackStack(null).commit()
        }

    }
}