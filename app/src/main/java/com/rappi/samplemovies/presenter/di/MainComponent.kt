package com.rappi.samplemovies.presenter.di

import com.rappi.samplemovies.presenter.di.detailmovie.MovieDetailsModule
import com.rappi.samplemovies.presenter.di.detailmovie.MovieDetailsSubComponent
import com.rappi.samplemovies.presenter.di.popularmovies.PopularMoviesModule
import com.rappi.samplemovies.presenter.di.popularmovies.PopularSubComponent
import com.rappi.samplemovies.presenter.di.searchmovies.SearchMoviesModule
import com.rappi.samplemovies.presenter.di.searchmovies.SearchSubComponent
import com.rappi.samplemovies.presenter.di.topratedmovies.TopRatedMoviesModule
import com.rappi.samplemovies.presenter.di.topratedmovies.TopRatedSubComponent
import com.rappi.samplemovies.presenter.di.upcomingmovies.UpComingMoviesModule
import com.rappi.samplemovies.presenter.di.upcomingmovies.UpComingSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class),
    (DataModule::class)
])

interface MainComponent {
    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent
    fun plus(topRatedMoviesModule: TopRatedMoviesModule): TopRatedSubComponent
    fun plus(upComingMoviesModule: UpComingMoviesModule): UpComingSubComponent
    fun plus(movieDetailsModule: MovieDetailsModule): MovieDetailsSubComponent
    fun plus(searchMoviesModule: SearchMoviesModule): SearchSubComponent
}