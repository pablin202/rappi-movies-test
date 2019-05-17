package com.rappi.samplemovies.presenter.di.detailmovie

import com.rappi.samplemovies.presenter.common.ASyncTransformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.usercases.GetMovieDetails
import com.rappi.samplemovies.presenter.detailmovie.MovieDetailsVMFactory
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsModule {

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository: IMoviesRepository): GetMovieDetails {
        return GetMovieDetails(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideMovieDetailsVMFactory(getMovieDetails: GetMovieDetails): MovieDetailsVMFactory {
        return MovieDetailsVMFactory(getMovieDetails)
    }

}