package com.example.moviedbapp.model.popularmoviesmodel

data class PopularMoviesModel(
    val page: Int,
    val results: List<PopularMoviesResult>,
    val total_pages: Int,
    val total_results: Int
)
