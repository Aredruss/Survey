package com.redbox.survey.ui.results

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.redbox.survey.R
import com.redbox.survey.domain.Test
import kotlinx.android.synthetic.main.item_mistakes.view.*
import java.io.InputStream

class MistakesListAdapter(val test: Test, val topic: String) :
    RecyclerView.Adapter<MistakesListAdapter.MistakesViewHolder>() {

    class MistakesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.imageView
        val userAnswer = itemView.user_answer_tv
        val solution = itemView.user_solution_tv
        val questionNum = itemView.question_num_tv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MistakesViewHolder {
        return MistakesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_mistakes, parent, false)
        )
    }

    override fun getItemCount() = test.lineUp.size

    override fun onBindViewHolder(holder: MistakesViewHolder, position: Int) {
        holder.questionNum.text = "Вопрос ${position + 1}"
        holder.userAnswer.text = test.answers[position]
        holder.solution.text = test.lineUp[position].picture.removeSuffix(".png")
        if (holder.solution.text != holder.userAnswer.text) {
            holder.questionNum.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.colorIncorrect
                )
            )

            holder.userAnswer.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.colorIncorrect
                )
            )
        }
        val stream: InputStream =
            holder.itemView.context.assets!!.open("$topic/${test.lineUp[position].picture}")
        val d = Drawable.createFromStream(stream, null)
        holder.image.setImageDrawable(d)
        stream.close()
    }
}