package ru.emba.cbs.features.course

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ru.emba.cbs.common.argumentNotNull
import ru.emba.cbs.common.model.CourseDto
import ru.emba.cbs.features.course.databinding.FragmentCourseBinding
import ru.emba.cbs.mvi.MviBindings
import javax.inject.Inject

@AndroidEntryPoint
class CourseFragment : Fragment(R.layout.fragment_course) {
    private val viewModel: CourseViewModel by viewModels { factory.create(course) }
    private var viewBinding: FragmentCourseBinding? = null
    private var course: CourseDto by argumentNotNull()
    @Inject lateinit var ui: CourseUi
    @Inject lateinit var mviBindings: MviBindings<CourseUi, CourseViewModel>
    @Inject lateinit var factory: CourseViewModel.AssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviBindings.setup(lifecycleScope, ui, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentCourseBinding.bind(view)
            .also { binding -> ui.bindViews(viewLifecycleOwner.lifecycleScope, binding) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
