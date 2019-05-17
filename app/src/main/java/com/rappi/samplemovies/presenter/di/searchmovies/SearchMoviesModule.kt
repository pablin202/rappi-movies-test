package com.rappi.samplemovies.presenter.di.searchmovies

import com.rappi.samplemovies.presenter.common.ASyncTransformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.usercases.SearchMovie
import com.rappi.samplemovies.presenter.search.SearchVMFactory
import dagger.Module
import dagger.Provides

@Module
class SearchMoviesModule {

    @Provides
    fun provideSearchMovieUseCase(moviesRepository: IMoviesRepository): SearchMovie {
        return SearchMovie(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideSearchVMFactory(useCase: SearchMovie): SearchVMFactory {
        return SearchVMFactory(useCase)
    }
}