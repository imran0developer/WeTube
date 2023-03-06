package com.imran.wetube

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.imran.wetube.Api_Package.ApiClient
import com.imran.wetube.Api_Package.ApiSet
import com.imran.wetube.Models.MsgModel
import com.imran.wetube.Models.UserModel
import com.imran.wetube.constants.Companion.DP
import com.imran.wetube.constants.Companion.ID
import com.imran.wetube.constants.Companion.NAME
import com.imran.wetube.constants.Companion.PASSWORD
import com.imran.wetube.constants.Companion.SHARED_PREFS
import com.imran.wetube.constants.Companion.USERNAME
import com.imran.wetube.constants.Companion.msg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (supportActionBar != null) {supportActionBar!!.hide()}
        setup()



        register.setOnClickListener{
            val name = name_et.text.toString()
            val username = username_et.text.toString()
            val password = password_et.text.toString()
            registerUser(name,username,password)
        }
        login.setOnClickListener{
            val username = username_et.text.toString()
            val password = password_et.text.toString()
            loginUser(username,password)
        }
        action.setOnClickListener {
            viewCheck()
        }


    }

    private fun viewCheck() {
        if (signupLayout.visibility == View.VISIBLE){
            loginLayout.visibility = View.VISIBLE
            signupLayout.visibility = View.GONE
            reason.text ="Signup as new user ? "
            heading.text ="Login"
        }else{
            loginLayout.visibility = View.GONE
            signupLayout.visibility = View.VISIBLE
            reason.text ="Joined us before ? "
            heading.text ="Sign Up"
        }
    }

    private fun registerUser(name: String, username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            apiSet.registerUser(name, username, password,"dp.jpg").enqueue(object : Callback<MsgModel?> {
                override fun onResponse(call: Call<MsgModel?>, response: Response<MsgModel?>) {

                    if (response.body()!=null) {
//                        msg(response.body()!!.message, this@LoginActivity)
                        if (response.body()!!.status.equals("true")){
                            loginUser(username,password)

                        }
                    }
                }

                override fun onFailure(call: Call<MsgModel?>, t: Throwable) {
                    msg(t.localizedMessage,this@LoginActivity)
                }
            })
        }

    }

    private fun loginUser(username: String, password: String) {

        CoroutineScope(Dispatchers.IO).launch {
            apiSet.loginUser(username, password).enqueue(object : Callback<UserModel?> {
                override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
                    if (response.body() != null) {
                        val user = response.body()!!.user[0]
                        val id = user.user_id
                        val name = user.name
                        val username = user.username
                        val password = user.password
                        val dp = user.user_dp
                        saveUser(id, name, username, password, dp)
                        startActivity(Intent(this@LoginActivity,StockVideosActivity::class.java))

                        Log.d("TAG2", "login $name $id")
                    }
                }

                override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                    msg(t.localizedMessage,this@LoginActivity)
                }
            })
        }
    }

    private fun setup() {
        inUi()
        val retrofit = ApiClient.getClient()
        apiSet = retrofit.create(ApiSet::class.java)

    }



    fun saveUser(id: String,name: String, username: String, password: String, dp: String){

        val sharedPreference =  getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString(ID,id)
        editor.putString(NAME,name)
        editor.putString(USERNAME,username)
        editor.putString(PASSWORD,password)
        editor.putString(DP,dp)
        editor.apply()
    }
    private fun inUi() {
     name_et = findViewById(R.id.name)
     username_et = findViewById(R.id.username)
     password_et = findViewById(R.id.password)
     register = findViewById(R.id.register_bt)
     login = findViewById(R.id.login_bt)
     reason = findViewById(R.id.reason_tv)
     heading = findViewById(R.id.heading)
     action = findViewById(R.id.action_tv)
     signupLayout = findViewById(R.id.sign_layout)
     loginLayout = findViewById(R.id.login_layout)
    }
    private lateinit var name_et : EditText
    private lateinit var username_et : EditText
    private lateinit var password_et : EditText
    private lateinit var register : Button
    private lateinit var login : Button
    private lateinit var heading : TextView
    private lateinit var reason : TextView
    private lateinit var action : TextView
    private lateinit var apiSet: ApiSet
    private lateinit var signupLayout: LinearLayout
    private lateinit var loginLayout: LinearLayout
}