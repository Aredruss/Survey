package com.redbox.survey.presentation.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.redbox.survey.R
import com.redbox.survey.presentation.home.HomeFragment
import com.redbox.survey.presentation.questions.QuestionFragment
import kotlinx.android.synthetic.main.fragment_results.*

class ResultsFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        results_again_btn.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, QuestionFragment()).addToBackStack(null).commit()
        }

        results_end_btn.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, HomeFragment()).addToBackStack(null).commit()
        }

        //TODO Display Results
    }
}