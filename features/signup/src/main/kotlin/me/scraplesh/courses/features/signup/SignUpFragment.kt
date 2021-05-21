package me.scraplesh.courses.features.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import me.scraplesh.courses.features.signup.databinding.FragmentSignupBinding

class SignUpFragment : Fragment(R.layout.fragment_signup) {
    private var viewBinding: FragmentSignupBinding? = null
//    private val ui: SignUpUi by scope.inject { parametersOf(childFragmentManager) }
//    private val bindings: MviBindings<SignUpUi> by scope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bindings.setup(ui)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSignupBinding.bind(view)
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
