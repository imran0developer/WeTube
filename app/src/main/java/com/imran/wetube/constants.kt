package com.imran.wetube

import android.content.Context
import android.widget.Toast

class constants{
    companion object{
        val LINK = "link"
        val ID = "id"
        val NAME = "name"
        val USERNAME = "username"
        val PASSWORD = "password"
        val DP = "dp"
        val SHARED_PREFS = "shared_prefs"

        fun msg(msg:String,context: Context){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }

    }
}