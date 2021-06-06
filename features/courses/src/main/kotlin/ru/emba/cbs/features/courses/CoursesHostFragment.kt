package ru.emba.cbs.features.courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ru.emba.cbs.common.defaultOnBackPressedCallback
import ru.emba.cbs.features.courses.databinding.FragmentCoursesHostBinding
import ru.emba.cbs.mvi.MviBindings
import javax.inject.Inject

@AndroidEntryPoint
class CoursesHostFragment : Fragment(R.layout.fragment_courses_host) {
    private val backPressedCallback by lazy {
        defaultOnBackPressedCallback(requireActivity()) { ui.onBackPress() }
    }
    private var viewBinding: FragmentCoursesHostBinding? = null
    @Inject lateinit var factory: CoursesHostUi.AssistedFactory
    @Inject lateinit var mviBindings: MviBindings<CoursesHostUi, Unit>
    private val ui: CoursesHostUi by lazy { factory.create(childFragmentManager, lifecycle) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
