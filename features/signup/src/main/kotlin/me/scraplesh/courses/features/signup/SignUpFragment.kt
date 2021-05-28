package me.scraplesh.courses.features.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import me.scraplesh.courses.features.signup.databinding.FragmentSignupBinding
import me.scraplesh.courses.mvi.MviBindings
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_signup) {
    private var viewBinding: FragmentSignupBinding? = null
    private val viewModel: SignUpViewModel by viewModels()
    @Inject lateinit var ui: SignUpUi
    @Inject lateinit var mviBindings: MviBindings<SignUpUi, SignUpViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviBindings.setup(lifecycleScope, ui, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSignupBinding.bind(view)
            .also { binding -> ui.bindViews(viewLifecycleOwner.lifecycleScope, binding) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
