package com.example.designapp.ui.lessons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import com.example.domain.Lesson
import com.example.designapp.R
import com.example.designapp.databinding.FragmentLessonsBinding
import com.example.designapp.ui.LessonsAdapterCallback
import com.example.designapp.utils.fragmentViewModel
import com.example.designapp.utils.openSkype

class LessonsFragment: Fragment(R.layout.fragment_lessons), KodeinAware, LessonsAdapterCallback {

    override val kodein: Kodein by closestKodein()
    private val binding: FragmentLessonsBinding by viewBinding()
    private val viewModel: LessonsViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LessonsAdapter(requireContext(), this)
        with(binding) {
            recyclerView.adapter = adapter
        }
        viewModel.lessonsLiveData.observe(viewLifecycleOwner) { result ->
            result.fold({
                adapter.lessons = it.toMutableList()
            }) {}
        }
    }

    override fun onSkypeClick(lesson: Lesson) {
        requireContext().openSkype()
    }

}