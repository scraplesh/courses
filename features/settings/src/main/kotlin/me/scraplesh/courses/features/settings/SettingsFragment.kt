package me.scraplesh.courses.features.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import me.scraplesh.courses.features.settings.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var viewBinding: FragmentSettingsBinding? = null
//    private val ui: SettingsUi by scope.inject { parametersOf(childFragmentManager) }
//    private val bindings: MviBindings<SettingsUi> by scope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bindings.setup(ui)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSettingsBinding.bind(view)
//            .also { binding -> ui.bindViews(binding) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        ui.unbindViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
