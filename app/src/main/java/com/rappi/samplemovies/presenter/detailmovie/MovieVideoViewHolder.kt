package com.rappi.samplemovies.presenter.detailmovie

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView

class MovieVideoViewHolder(itemView: View, private val onItemClickListener: AdapterView.OnItemClickListener?) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    internal var movieVideoThumbnail: ImageView? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (onItemClickListener != null) {
            //onItemClickListener!!.onItemClick() (adapterPosition, view)
        }
    }
}