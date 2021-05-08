package com.ilhamfidatama.androidwithkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private var movieList: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    fun deleteData(index: Int){
        movieList.removeAt(index)
        notifyItemRemoved(index)
    }
    fun updateData(movie: Movie,index: Int){
        movieList[index]=movie
        notifyItemChanged(index)
    }
    fun addData(movie: Movie){
        movieList.add(movie)
        notifyDataSetChanged()
    }
    fun insertData(movie: Movie, index: Int){
        movieList.add(index, movie)
        notifyItemInserted(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.binding(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun binding(movie: Movie) {
            itemView.tv_nama.text = movie.name
            itemView.tv_popular.text = movie.popularity.toString()

            Glide.with(itemView)
                .load(movie.imagePath)
                .apply(RequestOptions.circleCropTransform().override(50,50))
                .into(itemView.iv_logo)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "Movie ${movie.name}", Toast.LENGTH_SHORT).show()
            }

        }

    }
}