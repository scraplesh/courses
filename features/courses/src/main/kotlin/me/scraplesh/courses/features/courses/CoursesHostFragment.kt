package me.scraplesh.courses.features.courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import me.scraplesh.courses.common.defaultOnBackPressedCallback
import me.scraplesh.courses.features.courses.databinding.FragmentCoursesHostBinding
import me.scraplesh.courses.mvi.MviBindings
import javax.inject.Inject

@AndroidEntryPoint
class CoursesHostFragment : Fragment(R.layout.fragment_courses_host) {
    private val backPressedCallback by lazy {
        defaultOnBackPressedCallback(requireActivity()) { ui.onBackPress() }
    }
    private var viewBinding: FragmentCoursesHostBinding? = null
    @Inject lateinit var factory: CoursesHostUi.AssistedFactory
    @Inject lateinit var mviBindings: MviBindings<CoursesHostUi, Unit>
    private lateinit var ui: CoursesHostUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = factory.create(childFragmentManager, lifecycle)
        mviBindings.setup(lifecycleScope, ui, Unit)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, backPressedCallback)
    }

    override fun onPause() {
        super.onPause()
        backPressedCallback.remove()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
