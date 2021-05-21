package me.scraplesh.courses.features.onboarding

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.flow.filter
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.onboarding.databinding.FragmentOnboardingBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.view.clicks
import javax.inject.Inject

class OnboardingUi @Inject constructor() :
    Ui<OnboardingUi.Reaction, OnboardingUi.UiState, FragmentOnboardingBinding>() {

    enum class Reaction { SignUpClicked, SignInClicked }
    class UiState(val shouldShowToast: Boolean)

    private var slidesViewPager: ViewPager2 by didSet { }
    private var signUpButton: View by didSet {
        clicks().react { Reaction.SignUpClicked }
    }
    private var signInButton: View by didSet {
        clicks().react { Reaction.SignInClicked }
    }

    override fun bindViews(view: FragmentOnboardingBinding) {
        with(view) {
            slidesViewPager = viewpagerOnboardingSlides
            signUpButton = buttonOnboardingRegistration
            signInButton = textviewOnboardingAlreadyHaveAccount

            configureToasts(root.context)
        }
    }

    private fun configureToasts(context: Context) {
        states.filter { it.shouldShowToast }
            .subscribe { Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show() }
    }
}