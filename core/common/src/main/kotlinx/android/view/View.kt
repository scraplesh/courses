package android.view

import android.content.Context
import android.content.dip
import android.view.inputmethod.InputMethodManager

fun View.dip(value: Int): Int = context.dip(value)

fun View.showSoftKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideSoftKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}

fun View.postDelayed(delayMillis: Long, action: () -> Unit): Boolean =
    postDelayed(action, delayMillis)