package com.example.moviedbapp.apiservice

import com.example.moviedbapp.model.popularmoviesmodel.PopularMoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") accessKey: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesModel>

}