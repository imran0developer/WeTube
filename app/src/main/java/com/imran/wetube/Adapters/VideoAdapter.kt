package com.imran.wetube.Adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.imran.wetube.PexelsModels.PexelsVideo
import com.imran.wetube.R
import com.imran.wetube.VideoPlayActivity
import com.imran.wetube.constants.Companion.LINK
import com.squareup.picasso.Picasso

class VideoAdapter(private val context: Context ,private val pexelsVideoList: ArrayList<PexelsVideo>) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

//    private val videoList = ArrayList<Video>()
    private val fullList = ArrayList<PexelsVideo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return pexelsVideoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videos = pexelsVideoList[position]
        val video = videos.video_files[0]
        val video_str = video.link

     Picasso.get().load(videos.image).into(holder.imageView)


//      Log.d("TAG2", "onResponse: image ${video.image}")
        holder.play.setOnClickListener {
//        context.startActivity(Intent(context,VideoPlayActivity::class.java))
            val intent = Intent(context,VideoPlayActivity::class.java)
            intent.putExtra(LINK,video_str)
            context.startActivity(intent)
        }


    }

    fun updateList(newList : ArrayList<PexelsVideo>){
        pexelsVideoList.clear()
        fullList.clear()

        fullList.addAll(newList)
        pexelsVideoList.addAll(newList)

        notifyDataSetChanged()

    }



    inner class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView){

    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val play: TextView = itemView.findViewById(R.id.play)
    val upload: TextView = itemView.findViewById(R.id.upload)

//    val videoView: PlayerView = itemView.findViewById(R.id.exoPlayer)

    }

}