package com.rappi.samplemovies.presenter.topratedmovies

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.rappi.samplemovies.presenter.base.BaseFragmentLocalMovies
import com.rappi.samplemovies.presenter.common.app
import com.rappi.samplemovies.presenter.common.toast
import javax.inject.Inject

class TopRatedFragment : BaseFragmentLocalMovies() {

    @Inject
    lateinit var factory: TopRatedMoviesVMFactory
    private lateinit var viewModel: TopRatedMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.app!!.createTopRatedComponent().inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(TopRatedMoviesViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getTopRatedMovies()
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
        activity?.app!!.releaseTopRatedComponent()
    }
}
