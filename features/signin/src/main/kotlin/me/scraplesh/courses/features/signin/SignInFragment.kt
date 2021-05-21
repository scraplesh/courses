package me.scraplesh.courses.features.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import me.scraplesh.courses.features.signin.databinding.FragmentSigninBinding
import me.scraplesh.courses.mvi.MviBindings
import javax.inject.Inject

class SignInFragment : Fragment(R.layout.fragment_signin) {
    private var viewBinding: FragmentSigninBinding? = null
    private val viewModel: SignInViewModel by viewModels()
    @Inject lateinit var ui: SignInUi
    @Inject lateinit var mviBindings: MviBindings<SignInUi, SignInViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviBindings.setup(lifecycleScope, ui, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSigninBinding.bind(view)
            .also { binding -> ui.bindViews(viewLifecycleOwner.lifecycleScope, binding) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
