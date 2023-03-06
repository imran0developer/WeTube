package com.imran.wetube

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.wetube.Adapters.VideoAdapter
import com.imran.wetube.Api_Package.ApiClient
import com.imran.wetube.Api_Package.ApiSet
import com.imran.wetube.Api_Package.PexelsApiClient
import com.imran.wetube.Api_Package.PexelsApiSet
import com.imran.wetube.PexelsModels.PexelsVideo
import com.imran.wetube.PexelsModels.PexelsVideoModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StockVideosActivity : AppCompatActivity() {
    private lateinit var apiSet: ApiSet
    private lateinit var pexelsApiSet: PexelsApiSet
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var vidList: ArrayList<PexelsVideo>
    private lateinit var textView: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_stock_videos)

        val retrofit = ApiClient.getClient()
        apiSet = retrofit.create(ApiSet::class.java)

        vidList = ArrayList()

        initUI()
        getLinks()





    }
    private fun initUI() {
        vidList = ArrayList()
        val recyclerview = findViewById<RecyclerView>(R.id.rv)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        videoAdapter = VideoAdapter(this, vidList!!)
        recyclerview.adapter = videoAdapter
    }

    fun getLinks() {
        val retrofit2 = PexelsApiClient.getClient()
        pexelsApiSet = retrofit2.create(PexelsApiSet::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            pexelsApiSet.getVideos(1,10).enqueue(object : Callback<PexelsVideoModel?> {
                override fun onResponse(call: Call<PexelsVideoModel?>, response: Response<PexelsVideoModel?>) {
                    if (response.body()!=null){
                        Log.d("TAG2", "onResponse: Something ${response.body()!!.next_page}")
//                        vidList = response.body()!!.videos
                        vidList?.addAll(0,response.body()!!.pexelsVideos)

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

                override fun onFailure(call: Call<PexelsVideoModel?>, t: Throwable) {
                    Log.d("TAG2", "onFailure: fail")
                }
            })


    }
}
}