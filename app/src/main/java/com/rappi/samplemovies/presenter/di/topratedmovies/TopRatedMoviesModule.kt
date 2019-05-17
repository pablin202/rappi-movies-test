package com.rappi.samplemovies.presenter.di.topratedmovies

import com.rappi.samplemovies.presenter.common.ASyncTransformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.usercases.GetTopRatedMovies
import com.rappi.samplemovies.presenter.topratedmovies.TopRatedMoviesVMFactory
import dagger.Module
import dagger.Provides

@TopRatedScope
@Module
class TopRatedMoviesModule {

    @Provides
    fun provideGetTopRatedMoviesUseCase(moviesRepository: IMoviesRepository): GetTopRatedMovies {
        return GetTopRatedMovies(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideTopRatedMoviesVMFactory(useCase: GetTopRatedMovies): TopRatedMoviesVMFactory {
        return TopRatedMoviesVMFactory(useCase)
    }
}