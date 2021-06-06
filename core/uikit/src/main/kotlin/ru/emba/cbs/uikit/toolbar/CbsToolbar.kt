package ru.emba.cbs.uikit.toolbar

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import ru.emba.cbs.uikit.R
import ru.emba.cbs.uikit.databinding.LayoutToolbarBinding

class CbsToolbar : FrameLayout {
    var buttonClickListener: (() -> Unit)? = null
    private var titleText: String = ""
    private lateinit var titleView: TextView
    private lateinit var toolbarSize: CbsToolbarSize
    private val titleViewTopMargin get() = resources.getDimensionPixelOffset(R.dimen.spacing_32)
    private val titleViewStartMargin: Int
        get() {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)
            return TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
        readAttrs(context, attrs)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        titleView.configureTitle()
    }

    private fun initView() {
        LayoutToolbarBinding.inflate(LayoutInflater.from(context), this, true)
            .apply {
                buttonToolbar.setOnClickListener { buttonClickListener?.invoke() }
                titleView = textviewToolbar
            }
    }

    private fun readAttrs(context: Context, attrs: AttributeSet?) {
        with(context.obtainStyledAttributes(attrs, R.styleable.CbsToolbar)) {
            try {
                toolbarSize = CbsToolbarSize.values()
                    .getOrElse(getInteger(R.styleable.CbsToolbar_size, 0)) {
                        CbsToolbarSize.Large
                    }
                titleText = getResourceId(R.styleable.CbsToolbar_text, 0).takeIf { it > 0 }
                    ?.let { resourceId ->
                        try {
                            context.getString(resourceId)
                        } catch (_: Resources.NotFoundException) {
                            null
                        }
                    }
                    ?: getString(R.styleable.CbsToolbar_text) ?: ""
            } finally {
                recycle()
            }
        }
    }

    private fun TextView.configureTitle() {
        text = titleText
        (layoutParams as? LayoutParams)?.apply {
            gravity = when (toolbarSize) {
                CbsToolbarSize.Large -> {
                    setMargins(0, titleViewTopMargin, 0, 0)
                    Gravity.CENTER_HORIZONTAL
                }
                CbsToolbarSize.Regular -> {
                    setMargins(titleViewStartMargin, 0, 0, 0)
                    Gravity.CENTER_VERTICAL
                }
            }
        }
    }
}
