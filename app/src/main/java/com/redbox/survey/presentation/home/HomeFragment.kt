package com.redbox.survey.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.redbox.survey.R
import com.redbox.survey.presentation.questions.QuestionFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_start_cv.setOnClickListener {
            val manager = activity!!.supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment,
                    QuestionFragment()
                ).addToBackStack(null).commit()
        }
    }
}