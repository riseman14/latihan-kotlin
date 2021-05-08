package com.ilhamfidatama.androidwithkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.util.Util
import kotlinx.android.synthetic.main.fragment_movie.*



/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter(Utils.movies)
        movieAdapter.notifyDataSetChanged()
        rvMovie.adapter = movieAdapter
        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = LinearLayoutManager(context)

        btn_save.setOnClickListener{
            movieAdapter.addData(getForm())
            clearForm()
        }

        btn_delete.setOnClickListener{
            val index = getIndex()
            if (isNotOutRange(index)) movieAdapter.deleteData(index)
            else noticeOutRange()
            clearForm()
        }
        btn_update.setOnClickListener{
            val index = getIndex()
            if (isNotOutRange(index)) movieAdapter.updateData(getForm(), index)
            else noticeOutRange()
            clearForm()
        }
        btn_insert.setOnClickListener {
            val index = getIndex()
            if (isNotOutRange(index)) movieAdapter.insertData(getForm(),index)
            else noticeOutRange()
            clearForm()
        }

    }

    private fun getForm(): Movie {
        val name = pt_nama.text.toString()
        val popular = pt_popular.text.toString()
        val imageIndex = (0 until Utils.images.size).random()
        return Movie(name,popular.toDouble(),Utils.images[imageIndex])

    }

    private fun getIndex(): Int {
        val index = pt_index.text.toString()
        return if (index.isNotEmpty()) index.toInt() else 0
    }

    private fun clearForm() {
        pt_nama.setText("")
        pt_popular.setText("")
        pt_index.setText("")
    }

    private fun isNotOutRange(index: Int): Boolean = index < Utils.images.size

    private fun noticeOutRange(){
        Toast.makeText(context, "List out of range", Toast.LENGTH_LONG).show()
    }




}