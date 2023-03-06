package com.imran.wetube.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.wetube.Adapters.VideoAdapter
import com.imran.wetube.Api_Package.ApiSet
import com.imran.wetube.Api_Package.PexelsApiClient
import com.imran.wetube.Api_Package.PexelsApiSet
import com.imran.wetube.PexelsModels.PexelsVideo
import com.imran.wetube.PexelsModels.PexelsVideoModel
import com.imran.wetube.R
import com.imran.wetube.constants.Companion.msg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VideosFragment : Fragment() {

    private lateinit var apiSet: ApiSet
    private lateinit var pexelsApiSet: PexelsApiSet
    private lateinit var videoAdapter: VideoAdapter
    private var vidList: ArrayList<PexelsVideo>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_videos, container, false)
        vidList = ArrayList()
        val context = activity as Context
        val recyclerview = view.findViewById<RecyclerView>(R.id.videos_rv)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        videoAdapter = VideoAdapter(context, vidList!!)
        recyclerview.adapter = videoAdapter

        getData(context)

        return view
    }


    private fun getData(context: Context) {
        val retrofit2 = PexelsApiClient.getClient()
        pexelsApiSet = retrofit2.create(PexelsApiSet::class.java)

        pexelsApiSet.getVideos(1,10).enqueue(object : Callback<PexelsVideoModel?> {
            override fun onResponse(call: Call<PexelsVideoModel?>, response: Response<PexelsVideoModel?>) {
                if (response.body()!=null){

                    vidList?.addAll(response.body()!!.pexelsVideos)

                    videoAdapter.notifyDataSetChanged()
                    msg("geetting data", context = context)
                }
            }

            override fun onFailure(call: Call<PexelsVideoModel?>, t: Throwable) {
                msg(t.localizedMessage, context = context)
            }
        })


    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideosFragment().apply {

            }
    }
}