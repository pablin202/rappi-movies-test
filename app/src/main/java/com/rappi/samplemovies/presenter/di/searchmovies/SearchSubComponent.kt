package com.rappi.samplemovies.presenter.di.searchmovies

import com.rappi.samplemovies.presenter.search.SearchFragment
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchMoviesModule::class])
interface SearchSubComponent {
    fun inject(searchFragment: SearchFragment)
}