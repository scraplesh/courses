package me.scraplesh.courses.features.courses.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import me.scraplesh.courses.features.courses.R
import me.scraplesh.courses.features.courses.databinding.FragmentCoursesBinding

class CoursesFragment : Fragment(R.layout.fragment_courses) {
//    private val ui: CoursesUi by scope.inject()
//    private val bindings: MviBindings<CoursesUi> by scope.inject {
//        parametersOf(this, this)
//    }
    private var viewBinding: FragmentCoursesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycle.addObserver(ui)
//        bindings.setup(ui)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentCoursesBinding.bind(view)
//            .also { binding -> ui.bindViews(binding) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        ui.unbindViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
//        lifecycle.removeObserver(ui)
    }
}
