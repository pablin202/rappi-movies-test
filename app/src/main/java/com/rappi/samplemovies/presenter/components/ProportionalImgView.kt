package com.rappi.samplemovies.presenter.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class ProportionalImgView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    private val ASPECT_RATIO = 1.5f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = Math.round(width * ASPECT_RATIO)
        setMeasuredDimension(width, height)
    }
}