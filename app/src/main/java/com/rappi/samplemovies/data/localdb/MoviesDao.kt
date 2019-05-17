package com.rappi.samplemovies.data.localdb

import android.arch.persistence.room.*
import com.rappi.samplemovies.data.entities.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies WHERE type=1")
    fun getPopulars(): List<Movie>

    @Query("SELECT * FROM movies WHERE type=2")
    fun getTopRated(): List<Movie>

    @Query("SELECT * FROM movies WHERE type=3")
    fun getUpcoming(): List<Movie>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    fun get(movieId: Int): Movie?

    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun search(query: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Query("DELETE FROM movies WHERE type=1")
    fun deletePopulars()

    @Query("DELETE FROM movies WHERE type=2")
    fun deleteTopRates()

    @Query("DELETE FROM movies WHERE type=3")
    fun deleteUpcoming()

}