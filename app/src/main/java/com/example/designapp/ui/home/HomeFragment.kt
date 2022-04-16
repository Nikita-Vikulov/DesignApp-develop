package com.example.designapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import com.example.domain.Lesson
import com.example.designapp.R
import com.example.designapp.databinding.FragmentHomeBinding
import com.example.designapp.ui.LessonsAdapterCallback
import com.example.designapp.utils.fragmentViewModel
import com.example.designapp.utils.openSkype
import com.example.designapp.utils.showText

class HomeFragment : Fragment(R.layout.fragment_home), KodeinAware,
    LessonsAdapterCallback {

    override val kodein by closestKodein()
    private val binding: FragmentHomeBinding by viewBinding()
    private val homeViewModel: HomeViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val classesAdapter = LessonsPagerAdapter(requireContext(), this)
        val homeworkAdapter = HomeworkPagerAdapter(requireContext())
        with(binding) {
            timerView.timer = 5000
            lessons.adapter = classesAdapter
            homework.adapter = homeworkAdapter
            homeViewModel.lessonsLiveData.observe(viewLifecycleOwner) { result ->
                result.fold({
                    lessonsProgressBar.visibility = View.GONE
                    classesAdapter.lessons = it.toMutableList()
                    classesToday.text = getString(R.string.lessons_today, it.size.toString())
                }) {
                    showText(R.string.connection_failed)
                }
            }
            homeViewModel.homeworkLiveData.observe(viewLifecycleOwner) { result ->
                result.onSuccess {
                    homeworkProgressBar.visibility = View.GONE
                    homeworkAdapter.homework = it.toMutableList()
                }
            }
        }
    }

    override fun onSkypeClick(lesson: Lesson) {
        requireContext().openSkype()
    }

}