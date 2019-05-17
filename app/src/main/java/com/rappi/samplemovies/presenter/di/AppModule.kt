package com.rappi.samplemovies.presenter.di

import android.content.Context
import com.rappi.samplemovies.presenter.common.ImageLoader
import com.rappi.samplemovies.presenter.common.PicassoImageLoader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(context: Context){

    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideImageLoader() : ImageLoader {
        return PicassoImageLoader(Picasso.get())
    }
}