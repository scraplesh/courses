package ru.emba.cbs.features.timemanagement

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ru.emba.cbs.common.argumentNotNull
import ru.emba.cbs.common.model.CourseDto
import ru.emba.cbs.features.timemanagement.databinding.FragmentTimemanagementBinding
import ru.emba.cbs.mvi.MviBindings
import javax.inject.Inject

class TimeManagementFragment : Fragment(R.layout.fragment_timemanagement) {
    private val viewModel: TimeManagementViewModel by viewModels { factory.create(course) }
    private var viewBinding: FragmentTimemanagementBinding? = null
    private var course: CourseDto by argumentNotNull()
    @Inject lateinit var factory: TimeManagementViewModel.AssistedFactory
    @Inject lateinit var ui: TimeManagementUi
    @Inject lateinit var mviBindings: MviBindings<TimeManagementUi, TimeManagementViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviBindings.setup(lifecycleScope, ui, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding = FragmentTimemanagementBinding.bind(view)
            .also { binding -> ui.bindViews(viewLifecycleOwner.lifecycleScope, binding) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
