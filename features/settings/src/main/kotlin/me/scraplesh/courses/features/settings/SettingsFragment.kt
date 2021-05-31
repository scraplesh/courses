package me.scraplesh.courses.features.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import me.scraplesh.courses.common.argumentNotNull
import me.scraplesh.courses.common.model.CourseDto
import me.scraplesh.courses.features.settings.databinding.FragmentSettingsBinding
import me.scraplesh.courses.mvi.MviBindings
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val viewModel: SettingsViewModel by viewModels { factory.create(course) }
    private var viewBinding: FragmentSettingsBinding? = null
    private var course: CourseDto by argumentNotNull()
    @Inject lateinit var ui: SettingsUi
    @Inject lateinit var mviBindings: MviBindings<SettingsUi, SettingsViewModel>
    @Inject lateinit var factory: SettingsViewModel.AssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviBindings.setup(lifecycleScope, ui, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSettingsBinding.bind(view)
            .also { binding -> ui.bindViews(viewLifecycleOwner.lifecycleScope, binding) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
