package com.rappi.samplemovies.presenter.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.common.ImageLoader
import com.rappi.samplemovies.domain.models.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*

class SearchResultsAdapter constructor(
    private val imageLoader: ImageLoader,
    private val onMovieSelected: (MovieModel, View) -> Unit
) : RecyclerView.Adapter<SearchResultsAdapter.MovieCellViewHolder>() {


    private var movies: List<MovieModel> = listOf()
    var query: String? = null

    fun setResults(movies: List<MovieModel>, query: String?) {
        this.movies = movies
        this.query = query
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(
            R.layout.item_movie,
            p0,
            false
        )
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(p0: MovieCellViewHolder, p1: Int) {
        val movie = movies[p1]
        p0.bind(movie, imageLoader, onMovieSelected)
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieModel, imageLoader: ImageLoader, listener: (MovieModel, View) -> Unit) = with(itemView) {

            //            title.text = movie.originalTitle
//            rating.text = movie.voteAverage.toString()
//
//            movie.overview?.let {
//                overview.text = movie.overview
//                overview.visibility = View.VISIBLE
//            } ?: run {
//                overview.visibility = View.GONE
//            }
//
//            movie.posterPath?.let {
//                image.visibility = View.VISIBLE
//                imageLoader.load(it, image)
//            } ?: run { image.visibility == View.INVISIBLE }

            movie.posterPath?.let { imageLoader.load(movie.urlPoster, image) }
            setOnClickListener { listener(movie, itemView) }
        }

    }
}