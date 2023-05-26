package com.example.moviedbapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviedbapp.model.popularmoviesmodel.PopularMoviesModel
import com.example.moviedbapp.repository.PopularMovieRepositoryInterface
import com.example.moviedbapp.utils.PopularMoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val popularMoviesRepo: PopularMovieRepositoryInterface): ViewModel() {


    /** fetching popular movies from paging source using flow  **/
    val moviesList = Pager(PagingConfig(1)) {
        PopularMoviesPagingSource(popularMoviesRepo)
    }.flow.cachedIn(viewModelScope)




}