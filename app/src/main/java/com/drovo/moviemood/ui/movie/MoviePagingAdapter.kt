package com.drovo.moviemood.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.drovo.moviemood.BR
import com.drovo.moviemood.data.Movie
import com.drovo.moviemood.databinding.ViewHolderMovieBinding

class MoviePagingAdapter:PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(DIFF_UTIL) {

    var onClick: ((String)->Unit)?=null

    companion object{
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun onMovieClick(listener: (String)->Unit){
        onClick = listener
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderMovieBinding): RecyclerView.ViewHolder(viewDataBinding.root){

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        //BR means data binding R
        holder.viewDataBinding.setVariable(BR.movie, data)
        holder.viewDataBinding.root.setOnClickListener{
            onClick?.let {
                it(data?.imdbID!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}