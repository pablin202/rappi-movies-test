package com.rappi.samplemovies.presenter.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rappi.samplemovies.usercases.SearchMovie

class SearchVMFactory(private val searchMovie: SearchMovie): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(searchMovie) as T
    }

}