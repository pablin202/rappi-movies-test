package com.rappi.samplemovies.presenter.di.detailmovie

import com.rappi.samplemovies.presenter.detailmovie.DetailActivity
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {
    fun inject(movieDetailsActivity: DetailActivity)
}