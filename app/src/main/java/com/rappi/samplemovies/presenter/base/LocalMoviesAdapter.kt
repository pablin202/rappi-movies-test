package com.rappi.samplemovies.presenter.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.common.ImageLoader
import com.rappi.samplemovies.domain.models.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*

class LocalMoviesAdapter constructor(
    private val imageLoader: ImageLoader,
    private val onMovieSelected: (MovieModel, View) -> Unit
) : RecyclerView.Adapter<LocalMoviesAdapter.MovieCellViewHolder>(), Filterable {

    private val movies: MutableList<MovieModel> = mutableListOf()
    private val moviesListFiltered: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(
            R.layout.item_movie,
            p0,
            false
        )
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(view: MovieCellViewHolder, position: Int) {
        val movie = moviesListFiltered[position]
        view.bind(movie, imageLoader, onMovieSelected)
    }

    override fun getItemCount(): Int {
        return moviesListFiltered.size
    }

    fun addMovies(movies: List<MovieModel>) {
        this.movies.addAll(movies)
        this.moviesListFiltered.addAll(movies)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {

        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    moviesListFiltered.clear()
                    moviesListFiltered.addAll(movies)
                } else {

                    val filteredMovies: MutableList<MovieModel> = mutableListOf()
                    for (row in moviesListFiltered) {
                        if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredMovies.add(row)
                        }
                    }
                    moviesListFiltered.clear()
                    moviesListFiltered.addAll(filteredMovies)
                }

                return (FilterResults().apply {
                    values = moviesListFiltered
                })

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                notifyDataSetChanged()
            }
        }
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieModel, imageLoader: ImageLoader, listener: (MovieModel, View) -> Unit) = with(itemView) {
            movie.posterPath?.let { imageLoader.load(movie.urlPoster, image) }
            setOnClickListener { listener(movie, itemView) }
        }
    }
}