package com.example.moviedbapp.repository

import com.example.moviedbapp.apiservice.MovieService
import com.example.moviedbapp.model.popularmoviesmodel.PopularMoviesModel
import retrofit2.Response
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(private val movieService: MovieService):PopularMovieRepositoryInterface {
    override suspend fun fetchPopularMovies(token: String, page: Int): Response<PopularMoviesModel> {
        return movieService.getPopularMovies(token,page)
    }
}