package me.scraplesh.courses.features.timemanagement

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import me.scraplesh.courses.common.argumentNotNull
import me.scraplesh.courses.common.model.CourseDto
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.features.timemanagement.databinding.FragmentTimemanagementBinding

class TimeManagementFragment : Fragment(R.layout.fragment_timemanagement) {

    companion object {
        fun newInstance(course: Course) = TimeManagementFragment().apply {
            this.course = CourseDto(course)
        }
    }

    private var course: CourseDto by argumentNotNull()

    //    private val ui: TimeManagementUi by lifecycleScope.inject()
//    private val bindings: MviBindings<TimeManagementUi> by lifecycleScope.inject {
//        parametersOf(this, this, isInitial)
//    }
    private var viewBinding: FragmentTimemanagementBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycle.addObserver(ui)
//        bindings.setup(ui)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding = FragmentTimemanagementBinding.bind(view)
//            .also { binding -> ui.bindViews(binding) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        ui.unbindViews()
    }

    override fun onDestroy() {
        super.onDestroy()
//        lifecycle.removeObserver(ui)
        viewBinding = null
    }
}
