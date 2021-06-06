package ru.emba.cbs.common

import android.app.Activity
import androidx.activity.OnBackPressedCallback

fun defaultOnBackPressedCallback(activity: Activity, callback: () -> Boolean) =
    object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (callback().not()) {
                isEnabled = false
                activity.onBackPressed()
                isEnabled = true
            }
        }
    }