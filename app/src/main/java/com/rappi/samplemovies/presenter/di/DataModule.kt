package com.rappi.samplemovies.presenter.di

import android.arch.persistence.room.Room
import android.content.Context
import com.rappi.samplemovies.data.api.Api
import com.rappi.samplemovies.domain.interfaces.IMoviesCache
import com.rappi.samplemovies.data.localdb.Database
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.data.localdb.MoviesLocals
import com.rappi.samplemovies.data.repositories.LocalMoviesStore
import com.rappi.samplemovies.data.repositories.RemoteLocalStore
import com.rappi.samplemovies.data.repositories.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "movies_db").build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: Api,
                               cache: IMoviesCache
    ): IMoviesRepository {

        val cachedMoviesDataStore = LocalMoviesStore(cache)
        val remoteMoviesDataStore = RemoteLocalStore(api)
        return RepositoryImplementation(cachedMoviesDataStore, remoteMoviesDataStore)
    }

    @Singleton
    @Provides
    fun provideMoviesCache(moviesDatabase: Database): IMoviesCache {
        return MoviesLocals(moviesDatabase)
    }
}