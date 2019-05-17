package com.rappi.samplemovies.presenter.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.popularmovies.PopularMoviesFragment
import com.rappi.samplemovies.presenter.search.SearchFragment
import com.rappi.samplemovies.presenter.topratedmovies.TopRatedFragment
import com.rappi.samplemovies.presenter.upcomingmovies.UpcomingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PopularMoviesFragment(), "popular")
                .commitNow()
        }

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {

            R.id.action_popular -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PopularMoviesFragment(), "popular")
                    .commitNow()

            }

            R.id.action_top_rated -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TopRatedFragment(), "toprated")
                    .commitNow()

            }

            R.id.action_upcoming -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UpcomingFragment(), "upcoming")
                    .commitNow()
                title = getString(R.string.upcoming)
            }

            R.id.action_search -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchFragment(), "search")
                    .commitNow()

            }
        }

        true
    }

}
