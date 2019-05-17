package com.rappi.samplemovies.presenter.di.popularmovies

import com.rappi.samplemovies.presenter.common.ASyncTransformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.usercases.GetPopularMovies
import com.rappi.samplemovies.presenter.popularmovies.PopularMoviesVMFactory
import dagger.Module
import dagger.Provides

@PopularScope
@Module
class PopularMoviesModule {

    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: IMoviesRepository): GetPopularMovies {
        return GetPopularMovies(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun providePopularMoviesVMFactory(useCase: GetPopularMovies): PopularMoviesVMFactory {
        return PopularMoviesVMFactory(useCase)
    }
}