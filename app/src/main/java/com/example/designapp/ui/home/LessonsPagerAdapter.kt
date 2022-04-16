package com.example.designapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView
import com.example.data.utils.time
import com.example.domain.Lesson
import com.example.designapp.R
import com.example.designapp.databinding.LessonCardBinding
import com.example.designapp.ui.LessonsAdapterCallback
import com.example.designapp.utils.dp

class LessonsPagerAdapter(context: Context, private val callback: LessonsAdapterCallback) :
    RecyclerView.Adapter<LessonsPagerAdapter.LessonVH>() {

    var lessons = mutableListOf<Lesson>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LessonVH(
            LessonCardBinding.inflate(layoutInflater, parent, false).apply {
                root.layoutParams = (root.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    topMargin = 10.dp().toInt()
                    bottomMargin = 10.dp().toInt()
                    height = MATCH_PARENT
                }
            }, callback
        )

    override fun onBindViewHolder(holder: LessonVH, position: Int) {
        holder.bind(lessons[position])
    }

    override fun getItemCount() = lessons.size

    class LessonVH(private val binding: LessonCardBinding, private val callback: LessonsAdapterCallback) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) {
            with(binding) {
                lessonName.text = lesson.name
                lessonDescription.text = lesson.time()
                lessonDescription.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_schedule_24, 0, 0, 0)
                lessonDescription.compoundDrawablePadding = 5.dp().toInt()
                skypeCall.setOnClickListener {
                    callback.onSkypeClick(lesson)
                }
            }
        }
    }

}