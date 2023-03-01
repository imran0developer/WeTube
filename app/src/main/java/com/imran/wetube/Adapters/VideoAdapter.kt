package com.imran.wetube.Adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.imran.wetube.PexelsModels.Video
import com.imran.wetube.R
import com.squareup.picasso.Picasso

import kotlin.random.Random

class VideoAdapter(private val context: Context ,private val videoList: List<Video>) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

//    private val videoList = ArrayList<Video>()
    private val fullList = ArrayList<Video>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videoList[position]
    Picasso.get().load(video.image).into(holder.imageView)
//        holder.imageView.setImageResource(R.drawable.ic_launcher_background)

        Log.d("TAG2", "onResponse: image ${video.image}")
    }


    fun updateList(newList : ArrayList<Video>){
//        videoList.clear()
        fullList.clear()

        fullList.addAll(newList)
//        videoList.addAll(newList)

        notifyDataSetChanged()

    }



    inner class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView){
    val imageView = itemView.findViewById<ImageView>(R.id.imageView);

    }

}