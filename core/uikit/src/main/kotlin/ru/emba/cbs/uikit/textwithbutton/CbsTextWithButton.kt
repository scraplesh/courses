package ru.emba.cbs.uikit.textwithbutton

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import ru.emba.cbs.uikit.R
import ru.emba.cbs.uikit.databinding.LayoutTextWithButtonBinding

class CbsTextWithButton : LinearLayout {
    var buttonClickListener: (() -> Unit)? = null
    private var labelText: String = ""
    private var buttonText: String = ""
    private lateinit var textView: TextView
    private lateinit var button: Button

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
        textView.configureLabel()
        button.configureButton()
    }

    private fun initView() {
        LayoutTextWithButtonBinding.inflate(LayoutInflater.from(context), this, true)
            .apply {
                textView = textviewTextwithbutton
                button = buttonTextwithbutton.apply {
                    setOnClickListener { buttonClickListener?.invoke() }
                }
            }
    }

    private fun readAttrs(context: Context, attrs: AttributeSet?) {
        with(context.obtainStyledAttributes(attrs, R.styleable.CbsTextWithButton)) {
            try {
                labelText = getResourceId(R.styleable.CbsTextWithButton_labelText, 0).takeIf { it > 0 }
                    ?.let { resourceId ->
                        try {
                            context.getString(resourceId)
                        } catch (_: Resources.NotFoundException) {
                            null
                        }
                    }
                    ?: getString(R.styleable.CbsTextWithButton_labelText) ?: ""
                buttonText = getResourceId(R.styleable.CbsTextWithButton_buttonText, 0).takeIf { it > 0 }
                    ?.let { resourceId ->
                        try {
                            context.getString(resourceId)
                        } catch (_: Resources.NotFoundException) {
                            null
                        }
                    }
                    ?: getString(R.styleable.CbsTextWithButton_buttonText) ?: ""
            } finally {
                recycle()
            }
        }
    }

    private fun TextView.configureLabel() {
        text = labelText
    }

    private fun TextView.configureButton() {
        text = buttonText
    }
}
