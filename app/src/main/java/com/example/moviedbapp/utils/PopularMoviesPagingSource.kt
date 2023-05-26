package com.example.moviedbapp.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.moviedbapp.model.popularmoviesmodel.PopularMoviesResult
import com.example.moviedbapp.repository.PopularMovieRepositoryInterface

class PopularMoviesPagingSource(
    private val repository: PopularMovieRepositoryInterface,
) : PagingSource<Int, PopularMoviesResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMoviesResult> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.fetchPopularMovies("87fc1d42a8c83d5f9ac3cb531bcb783e", currentPage)
            val data = response.body()!!.results
            val responseData = mutableListOf<PopularMoviesResult>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMoviesResult>): Int? {
        return null
    }
}