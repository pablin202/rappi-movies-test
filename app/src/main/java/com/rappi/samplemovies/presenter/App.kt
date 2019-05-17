package com.rappi.samplemovies.presenter

import android.app.Application
import com.rappi.samplemovies.presenter.di.*
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

class App : Application() {

    private val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule())
            .dataModule(DataModule())
            .build()
    }

    private var popularMoviesComponent: PopularSubComponent? = null
    private var topRatedMoviesComponent: TopRatedSubComponent? = null
    private var upComingMoviesComponent: UpComingSubComponent? = null
    private var movieDetailsComponent: MovieDetailsSubComponent? = null
    private var searchMovieComponent: SearchSubComponent? = null

    fun createPopularComponent(): PopularSubComponent {
        popularMoviesComponent = mainComponent.plus(PopularMoviesModule())
        return popularMoviesComponent!!
    }

    fun releasePopularComponent() {
        popularMoviesComponent = null
    }

    fun createTopRatedComponent(): TopRatedSubComponent {
        topRatedMoviesComponent = mainComponent.plus(TopRatedMoviesModule())
        return topRatedMoviesComponent!!
    }

    fun releaseTopRatedComponent() {
        topRatedMoviesComponent = null
    }

    fun createUpComingComponent(): UpComingSubComponent {
        upComingMoviesComponent = mainComponent.plus(UpComingMoviesModule())
        return upComingMoviesComponent!!
    }

    fun releaseUpComingComponent() {
        upComingMoviesComponent = null
    }

    fun createDetailsComponent(): MovieDetailsSubComponent {
        movieDetailsComponent = mainComponent.plus(MovieDetailsModule())
        return movieDetailsComponent!!
    }

    fun releaseDetailsComponent() {
        movieDetailsComponent = null
    }

    fun createSearchComponent(): SearchSubComponent {
        searchMovieComponent = mainComponent.plus(SearchMoviesModule())
        return searchMovieComponent!!
    }

    fun releaseSearchComponent() {
        searchMovieComponent = null
    }

}