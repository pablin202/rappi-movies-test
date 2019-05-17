package com.rappi.samplemovies.presenter.di.upcomingmovies

import com.rappi.samplemovies.presenter.common.ASyncTransformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.usercases.GetUpComingMovies
import com.rappi.samplemovies.presenter.upcomingmovies.UpComingVMFactory
import dagger.Module
import dagger.Provides

@UpComingScope
@Module
class UpComingMoviesModule {

    @Provides
    fun provideGetUpComingMoviesUseCase(moviesRepository: IMoviesRepository): GetUpComingMovies {
        return GetUpComingMovies(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideUpComingMoviesVMFactory(useCase: GetUpComingMovies): UpComingVMFactory {
        return UpComingVMFactory(useCase)
    }
}