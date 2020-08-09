package com.redbox.survey.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redbox.survey.R
import com.redbox.survey.domain.TestTopicsRepository
import kotlinx.android.synthetic.main.item_test_topic.view.*

class TestTopicRvAdapter(val startTestOnTopic: (String) -> Unit) :
    RecyclerView.Adapter<TestTopicRvAdapter.TopicVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicVH {
        return TopicVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_test_topic,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = TestTopicsRepository.topics.size

    override fun onBindViewHolder(holder: TopicVH, position: Int) {
        holder.onBind(TestTopicsRepository.topics[position])
    }

    inner class TopicVH(private val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(topic: Pair<String, Int>) {
            view.subject_tv.text = view.context.getText(topic.second)
            view.cv_topic_item.setOnClickListener {
                startTestOnTopic(topic.first)
            }
        }
    }

}