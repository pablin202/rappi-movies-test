package com.rappi.samplemovies.data.localdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.rappi.samplemovies.data.entities.Movie

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}