package com.rappi.samplemovies.presenter.upcomingmovies

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.base.BaseFragmentLocalMovies
import com.rappi.samplemovies.presenter.common.app
import com.rappi.samplemovies.presenter.common.toast
import javax.inject.Inject


class UpcomingFragment : BaseFragmentLocalMovies() {

    @Inject
    lateinit var factory: UpComingVMFactory
    private lateinit var viewModel: UpComingMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.app!!.createUpComingComponent().inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(UpComingMoviesViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getUpComingMovies()
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
        activity?.app!!.releaseUpComingComponent()
    }
}
