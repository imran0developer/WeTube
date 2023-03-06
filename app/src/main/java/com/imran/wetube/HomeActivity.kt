package com.imran.wetube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.wetube.Adapters.VideoAdapter
import com.imran.wetube.Fragments.VideosFragment
import com.imran.wetube.PexelsModels.PexelsVideo

class HomeActivity : AppCompatActivity() {
    private lateinit var videoAdapter : VideoAdapter
    private lateinit var vidList:ArrayList<PexelsVideo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val videosFragment = VideosFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout,videosFragment)
        fragmentTransition.commit()



}
    private fun initUI() {
        val recyclerview : RecyclerView = findViewById(R.id.rv)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        videoAdapter = VideoAdapter(this, vidList!!)
        recyclerview.adapter = videoAdapter
    }



    /*button.setOnClickListener {

    }*/
}


