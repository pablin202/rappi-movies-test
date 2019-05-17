package com.rappi.samplemovies.presenter.detailmovie

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.common.VideoItemListener
import com.rappi.samplemovies.presenter.common.inflate
import com.rappi.samplemovies.domain.models.VideoModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_movie_video.view.*


class MovieVideosAdapter(private val listener: VideoItemListener) :
    RecyclerView.Adapter<MovieVideosAdapter.ViewHolder>() {

    private var movieVideos: List<VideoModel>? = null

    fun setMovieVideos(movieVideos: List<VideoModel>?) {
        this.movieVideos = movieVideos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ViewHolder(parent.inflate(R.layout.list_item_movie_video))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movieVideos!![position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(video: VideoModel, listener: VideoItemListener) = with(itemView) {

            Picasso.get()
                .load(String.format(YOUTUBE_THUMBNAIL, video.youtubeKey))
                .into(movie_video_thumbnail)

            setOnClickListener { listener.onClick(video, adapterPosition) }
        }
    }

    override fun getItemCount(): Int {
        return if (movieVideos == null) {
            0
        } else movieVideos!!.size
    }

    companion object {
        private val YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/mqdefault.jpg"
    }
}