package com.rappi.samplemovies.presenter.di.upcomingmovies

import com.rappi.samplemovies.presenter.upcomingmovies.UpcomingFragment
import dagger.Subcomponent

@Subcomponent(modules = [UpComingMoviesModule::class])
interface UpComingSubComponent {
    fun inject(upcomingFragment: UpcomingFragment)
}