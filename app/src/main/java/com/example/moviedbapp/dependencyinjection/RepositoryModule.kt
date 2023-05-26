package com.example.moviedbapp.dependencyinjection

import com.example.moviedbapp.apiservice.MovieService
import com.example.moviedbapp.repository.PopularMovieRepositoryInterface
import com.example.moviedbapp.repository.PopularMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {



    @Provides
    @Singleton
    fun providePopularMovies(
        movieService: MovieService
    ): PopularMovieRepositoryInterface{
        return PopularMoviesRepository(movieService)

    }
}