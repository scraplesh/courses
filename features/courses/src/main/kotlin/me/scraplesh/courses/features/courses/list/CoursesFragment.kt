package me.scraplesh.courses.features.courses.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import me.scraplesh.courses.features.courses.R
import me.scraplesh.courses.features.courses.databinding.FragmentCoursesBinding
import me.scraplesh.courses.mvi.MviBindings
import javax.inject.Inject

@AndroidEntryPoint
class CoursesFragment : Fragment(R.layout.fragment_courses) {
    private val viewModel: CoursesViewModel by viewModels()
    private var viewBinding: FragmentCoursesBinding? = null
    private lateinit var ui: CoursesUi
    @Inject lateinit var mviBindings: MviBindings<CoursesUi, CoursesViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviBindings.setup(lifecycleScope, ui, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentCoursesBinding.bind(view)
            .also { binding -> ui.bindViews(viewLifecycleOwner.lifecycleScope, binding) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
