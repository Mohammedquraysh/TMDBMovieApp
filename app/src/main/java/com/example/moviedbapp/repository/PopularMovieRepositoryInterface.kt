package com.example.moviedbapp.repository

import com.example.moviedbapp.model.popularmoviesmodel.PopularMoviesModel
import retrofit2.Response

interface PopularMovieRepositoryInterface {
    suspend fun fetchPopularMovies(token: String, page: Int): Response<PopularMoviesModel>

}