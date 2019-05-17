package com.rappi.samplemovies.presenter.popularmovies

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.base.GeneralMoviesViewState
import com.rappi.samplemovies.presenter.base.BaseFragmentLocalMovies
import com.rappi.samplemovies.presenter.common.app
import com.rappi.samplemovies.presenter.common.toast
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class PopularMoviesFragment : BaseFragmentLocalMovies() {

    @Inject
    lateinit var factory: PopularMoviesVMFactory
    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.app!!.createPopularComponent().inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(PopularMoviesViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getPopularMovies()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewState.observe(this, android.arch.lifecycle.Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, android.arch.lifecycle.Observer { throwable ->
            throwable?.let {
                activity?.toast(throwable.message!!)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.app!!.releasePopularComponent()
    }
}
