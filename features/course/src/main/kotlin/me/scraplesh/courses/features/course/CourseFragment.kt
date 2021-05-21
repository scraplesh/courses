package me.scraplesh.courses.features.course

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import me.scraplesh.courses.features.course.databinding.FragmentCourseBinding

class CourseFragment : Fragment(R.layout.fragment_course) {
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
//            if (!ui.onBackPress()) {
//                isEnabled = false
//                activity?.onBackPressed()
//                isEnabled = true
//            }
        }
    }
    private var viewBinding: FragmentCourseBinding? = null
//    private val bindings: MviBindings<CourseUi> by scope.inject {
//        parametersOf(this, this)
//    }
//    private val ui: CourseUi by scope.inject { parametersOf(childFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bindings.setup(ui)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentCourseBinding.bind(view)
//            .also { binding -> ui.bindViews(binding) }
    }

    override fun onResume() {
        super.onResume()
        activity?.onBackPressedDispatcher
            ?.addCallback(viewLifecycleOwner, backPressedCallback)
    }

    override fun onPause() {
        super.onPause()
        backPressedCallback.remove()
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        ui.unbindViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden) {
            backPressedCallback.isEnabled = false
        } else {
            backPressedCallback.isEnabled = true
//            ui.onShow()
        }
    }
}
