package com.rappi.samplemovies.presenter.di.topratedmovies

import com.rappi.samplemovies.presenter.topratedmovies.TopRatedFragment
import dagger.Subcomponent

@Subcomponent(modules = [TopRatedMoviesModule::class])
interface TopRatedSubComponent {
    fun inject(topRatedFragment: TopRatedFragment)
}