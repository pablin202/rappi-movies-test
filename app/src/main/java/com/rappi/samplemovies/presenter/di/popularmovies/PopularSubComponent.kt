package com.rappi.samplemovies.presenter.di.popularmovies

import com.rappi.samplemovies.presenter.popularmovies.PopularMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularSubComponent {
    fun inject(popularMoviesFragment: PopularMoviesFragment)
}