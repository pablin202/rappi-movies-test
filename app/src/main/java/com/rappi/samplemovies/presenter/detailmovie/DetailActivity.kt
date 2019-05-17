package com.rappi.samplemovies.presenter.detailmovie

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.common.ImageLoader
import com.rappi.samplemovies.presenter.common.RxBus
import com.rappi.samplemovies.presenter.common.TransitionCallback
import com.rappi.samplemovies.presenter.common.app

import kotlinx.android.synthetic.main.activity_detail_movie.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieDetailsVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var detailsViewModel: MovieDetailsViewModel


    companion object {
        const val MOVIE_ID: String = "movie_id"
        const val MOVIE_TITLE: String = "movie_title"
        const val MOVIE_POSTER_URL: String = "movie_poster_url"
        const val MOVIE_BACKDROP_URL: String = "movie_backdrop_url"
        const val MOVIE_RELEASE_DATE: String = "movie_release"
        const val MOVIE_OVERVIEW: String = "movie_overview"
        const val MOVIE_VOTE_AVERAGE: String = "movie_average"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        postponeEnterTransition()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_FULLSCREEN
        app.createDetailsComponent().inject(this)

        factory.movieId = intent.getIntExtra(MOVIE_ID, 0)
        detailsViewModel = ViewModelProviders.of(this, factory).get(MovieDetailsViewModel::class.java)

        val backdropUrl = intent.getStringExtra(MOVIE_BACKDROP_URL)
        val title = intent.getStringExtra(MOVIE_TITLE)
        val posterUrl = intent.getStringExtra(MOVIE_POSTER_URL)
        val overview = intent.getStringExtra(MOVIE_OVERVIEW)
        val vote = intent.getDoubleExtra(MOVIE_VOTE_AVERAGE, 0.0)
        val releaseDate = intent.getStringExtra(MOVIE_RELEASE_DATE)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.movies_grid_container,
                    MovieDetailFragment.newInstance(posterUrl, title, overview, vote, releaseDate)
                )
                .commit()
        }

        initToolbar(title, backdropUrl)

        ViewCompat.setElevation(
            nestedScrollView,
            convertDpToPixel(resources.getInteger(R.integer.movie_detail_content_elevation_in_dp).toFloat())
        )

        window.sharedElementEnterTransition.addListener(TransitionCallback {
            observeViewState()
        })

        if (savedInstanceState != null) {
            observeViewState()
        } else {
            detailsViewModel.getMovieDetails()
        }
    }


    private fun observeViewState() {
        detailsViewModel.viewState.observe(this, Observer { viewState -> handleViewState(viewState) })
        detailsViewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun handleViewState(state: MovieDetailsViewState?) {
        state?.videos?.let {
            RxBus.publish(state.copy())
        }
    }

    private fun initToolbar(titleMovie: String?, pictureBackUrl: String?) {

        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            toolbar.setNavigationOnClickListener { onBackPressed() }
        }
        collapsing_toolbar_layout.title = titleMovie
        collapsing_toolbar_layout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
        title = ""

        pictureBackUrl?.let {
            imageLoader.load(it, backdrop_image) {
                startPostponedEnterTransition()
            }
        } ?: run {
            startPostponedEnterTransition()
        }
    }

    private fun convertDpToPixel(dp: Float): Float {
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    override fun onDestroy() {
        super.onDestroy()
        app.releaseDetailsComponent()
    }
}
