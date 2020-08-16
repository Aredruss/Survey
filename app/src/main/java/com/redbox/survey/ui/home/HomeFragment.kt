package com.redbox.survey.ui.home

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.survey.R
import com.redbox.survey.domain.QuestionRepository
import com.redbox.survey.ui.questions.QuestionFragment
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

        rv_test_topics.layoutManager = LinearLayoutManager(activity)
        rv_test_topics.adapter = TestTopicRvAdapter(this::startTestOnTopic)
    }

    private fun startTestOnTopic(subfolder: String) {
        QuestionRepository.setTest(activity?.assets?.list(subfolder)!!.asList())
        val manager = activity!!.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment,
                QuestionFragment(subfolder)
            ).addToBackStack(null).commit()
    }
}