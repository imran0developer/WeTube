package com.imran.wetube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.wetube.Adapters.VideoAdapter
import com.imran.wetube.Api_Package.ApiClient
import com.imran.wetube.Api_Package.ApiSet
import com.imran.wetube.PexelsModels.Video
import com.imran.wetube.PexelsModels.VideoModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    private lateinit var apiSet: ApiSet
    private lateinit var videoAdapter: VideoAdapter
    private var vidList: ArrayList<Video>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val retrofit = ApiClient.getClient()
        apiSet = retrofit.create(ApiSet::class.java)
        vidList = ArrayList()

        val recyclerview = findViewById<RecyclerView>(R.id.rv)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        videoAdapter = VideoAdapter(this, vidList!!)
        recyclerview.adapter = videoAdapter


        initUI()
        getLinks()

    }
    private fun initUI() {
/*        b.rv.setHasFixedSize(true)
        b.rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        videoAdapter = VideoAdapter(this, vidList!!)
        b.rv.adapter = videoAdapter*/
    }

    fun getLinks() {
        CoroutineScope(Dispatchers.IO).launch {

            apiSet.getVideos(1,10).enqueue(object : Callback<VideoModel?> {
                override fun onResponse(call: Call<VideoModel?>, response: Response<VideoModel?>) {
                    if (response.body()!=null){
                        Log.d("TAG2", "onResponse: Something ${response.body()!!.next_page}")
//                        vidList = response.body()!!.videos
                        vidList?.addAll(response.body()!!.videos)

                        videoAdapter.notifyDataSetChanged()
                    /*    for (video in vidList!!){
                            Log.d("TAG2", "onResponse: image ${video.image}")

                        }*/
                       /* val video =  vidList!![0]
//                        video.image
                        Log.d("TAG2", "onResponse: image ${video.image}")
*/

                    }
                }

                override fun onFailure(call: Call<VideoModel?>, t: Throwable) {
                    Log.d("TAG2", "onFailure: fail")
                }
            })


    }
}
}