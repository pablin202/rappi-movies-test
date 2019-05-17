package com.rappi.samplemovies.presenter.base

import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.app.Fragment
import android.util.Pair
import android.view.View
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.detailmovie.DetailActivity
import com.rappi.samplemovies.domain.models.MovieModel

open class BaseFragment : Fragment() {

    private lateinit var moviesAdapter: LocalMoviesAdapter

    protected fun navigateToMovieDetailsActivity(movie: MovieModel, view: View) {

        var activityOptions: ActivityOptions? = null

        val imageForTransition: View? = view.findViewById(R.id.image)
        imageForTransition?.let {
            val posterSharedElement: Pair<View, String> = Pair.create(it, getString(R.string.transition_picture))
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, posterSharedElement)
        }

        var intent = Intent(context!!, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID, movie.id)
        intent.putExtra(DetailActivity.MOVIE_POSTER_URL, movie.urlPoster)
        intent.putExtra(DetailActivity.MOVIE_BACKDROP_URL, movie.urlBackDropPath)
        intent.putExtra(DetailActivity.MOVIE_TITLE, movie.title)
        intent.putExtra(DetailActivity.MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(DetailActivity.MOVIE_OVERVIEW, movie.overview)
        intent.putExtra(DetailActivity.MOVIE_VOTE_AVERAGE, movie.voteAverage)

        startActivity(
            intent, activityOptions?.toBundle()
        )

        activity?.overridePendingTransition(0, 0)
    }
}