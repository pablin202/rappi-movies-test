package com.rappi.samplemovies.presenter.common

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemOffsetDecoration(context: Context, itemOffsetId: Int) : RecyclerView.ItemDecoration() {

    private var mItemOffset: Int = 0

    init {
        mItemOffset = context.resources.getDimensionPixelSize(
            itemOffsetId
        )
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}