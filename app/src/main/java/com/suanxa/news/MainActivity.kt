package com.suanxa.news

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.suanxa.news.api.ApiClient
import com.suanxa.news.models.RegisterRequest
import com.suanxa.news.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag

class MainActivity : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnRegister = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)


        //ke halaman Login
        btnLogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
        //Ke halaman register
        btnRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

}