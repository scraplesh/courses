package android.content

fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
