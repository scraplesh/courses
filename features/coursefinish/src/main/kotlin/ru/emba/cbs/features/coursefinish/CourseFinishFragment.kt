package ru.emba.cbs.features.coursefinish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ru.emba.cbs.features.coursefinish.databinding.FragmentCourseFinishBinding
import ru.emba.cbs.mvi.MviBindings
import javax.inject.Inject

@AndroidEntryPoint
class CourseFinishFragment : Fragment(R.layout.fragment_course_finish) {
    private var viewBinding: FragmentCourseFinishBinding? = null
    private val viewModel: CourseFinishViewModel by viewModels()
    @Inject lateinit var mviBindings: MviBindings<CourseFinishUi, CourseFinishViewModel>
    @Inject lateinit var ui: CourseFinishUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviBindings.setup(lifecycleScope, ui, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding = FragmentCourseFinishBinding.bind(view)
            .also { binding -> ui.bindViews(viewLifecycleOwner.lifecycleScope, binding) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
