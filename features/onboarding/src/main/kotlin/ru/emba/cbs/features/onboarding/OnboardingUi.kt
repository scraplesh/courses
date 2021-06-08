package ru.emba.cbs.features.onboarding

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipDrawable
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.emba.cbs.common.didSet
import ru.emba.cbs.features.onboarding.databinding.FragmentOnboardingBinding
import ru.emba.cbs.features.onboarding.databinding.ItemOnboardingBinding
import ru.emba.cbs.mvi.Ui
import ru.ldralighieri.corbind.view.clicks
import javax.inject.Inject

class OnboardingUi @Inject constructor() :
    Ui<OnboardingUi.Reaction, OnboardingUi.UiState, FragmentOnboardingBinding>() {

    enum class Reaction { SignUpClicked, SignInClicked }
    class UiState(val items: List<Item>)

    private var slidesListView: RecyclerView by didSet {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(this)
        addItemDecoration(DotsIndicatorDecoration(context))
        adapter = ListDelegationAdapter(createDelegate()).apply {
            states.subscribe {
                items = it.items
                notifyDataSetChanged()
            }
        }
    }
    private var signUpButton: View by didSet {
        clicks().react { Reaction.SignUpClicked }
    }
    private var signInButton: TextView by didSet {
        clicks().react { Reaction.SignInClicked }

        val chipDrawable = ChipDrawable.createFromResource(context, R.xml.chip_button)
            .apply {
                setText(R.string.onboarding_sign_in)
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            }
        val stringFirstPart = context.getString(R.string.onboarding_already_have_account)
        val string = SpannableString(stringFirstPart)
        string.setSpan(
            ImageSpan(chipDrawable),
            18,
            23,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = string
    }

    override fun bindViews(view: FragmentOnboardingBinding) {
        with(view) {
            slidesListView = recyclerviewOnboarding
            signUpButton = buttonOnboardingSignUp
            signInButton = textviewOnboardingAlreadyHaveAccount
        }
    }

    private fun createDelegate(): AdapterDelegate<List<Item>> =
        adapterDelegateViewBinding(
            { inflater, parent -> ItemOnboardingBinding.inflate(inflater, parent, false) }
        ) {
            bind {
                with(binding) {
                    textviewItemonboardingTitle.text = context.getString(item.title)
                    textviewItemonboardingDescription.text = context.getString(item.description)
                }
            }
        }

    class Item(@StringRes val title: Int, @StringRes val description: Int)
}
