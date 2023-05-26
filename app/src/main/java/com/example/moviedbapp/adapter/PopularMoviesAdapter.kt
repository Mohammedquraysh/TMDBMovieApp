package com.example.moviedbapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.databinding.PopularMoviesItemBinding
import com.example.moviedbapp.model.popularmoviesmodel.PopularMoviesResult
import com.example.moviedbapp.utils.Constants.IMAGE_URL
import javax.inject.Inject


class PopularMoviesAdapter @Inject() constructor() :
    PagingDataAdapter<PopularMoviesResult, PopularMoviesAdapter.ViewHolder>(differCallback) {

    private lateinit var binding: PopularMoviesItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = PopularMoviesItemBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindView(movie: PopularMoviesResult) {
            binding.apply {
                Glide.with(rvItemImageView.context)
                    .load(IMAGE_URL + movie.poster_path)
                    .into(rvItemImageView)
                rvTitleTv.text = movie.title
                rvItemReleasedDateContentTv.text = movie.release_date
            }
        }
    }


    companion object {
        val differCallback = object : DiffUtil.ItemCallback<PopularMoviesResult>() {
            override fun areItemsTheSame(oldItem: PopularMoviesResult, newItem: PopularMoviesResult): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: PopularMoviesResult, newItem: PopularMoviesResult): Boolean {
                return oldItem == newItem
            }
        }
    }

}
