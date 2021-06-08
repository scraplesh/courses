package ru.emba.cbs.features.onboarding

import android.content.Context
import android.content.dip
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class DotsIndicatorDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val radius = context.dip(6).toFloat()
    private val indicatorHeight = radius * 2
    private val indicatorItemPadding: Int = context.dip(12)
    private val inactivePaint: Paint = Paint().apply {
        strokeCap = Paint.Cap.ROUND
        strokeWidth = context.resources.displayMetrics.density
        style = Paint.Style.FILL
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.mischka)
    }
    private val activePaint: Paint = Paint().apply {
        strokeCap = Paint.Cap.ROUND
        strokeWidth = context.resources.displayMetrics.density
        style = Paint.Style.FILL
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.abbey)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val adapter = parent.adapter ?: return
        val itemCount: Int = adapter.itemCount

        // center horizontally, calculate width and subtract half from center
        val totalLength = (radius * 2 * itemCount).toFloat()
        val paddingBetweenItems = (0.coerceAtLeast(itemCount - 1) * indicatorItemPadding).toFloat()
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX: Float = (parent.width - indicatorTotalWidth) / 2f

        // center vertically in the allotted space
        val indicatorPosY: Float = parent.height - indicatorHeight / 2f
        drawInactiveDots(c, indicatorStartX, indicatorPosY, itemCount)
        val activePosition =
            (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) return

        // find offset of active page if the user is scrolling
        parent.layoutManager?.findViewByPosition(activePosition) ?: return
        drawActiveDot(c, indicatorStartX, indicatorPosY, activePosition)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = indicatorHeight.roundToInt()
    }

    private fun drawInactiveDots(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int
    ) {
        // width of item indicator including padding
        val itemWidth = (radius * 2 + indicatorItemPadding).toFloat()
        var start = indicatorStartX + radius
        for (i in 0 until itemCount) {
            c.drawCircle(start, indicatorPosY, radius, inactivePaint)
            start += itemWidth
        }
    }

    private fun drawActiveDot(
        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
        highlightPosition: Int
    ) {
        // width of item indicator including padding
        val itemWidth = (radius * 2 + indicatorItemPadding).toFloat()
        val highlightStart = indicatorStartX + radius + itemWidth * highlightPosition
        c.drawCircle(highlightStart, indicatorPosY, radius, activePaint)
    }
}
