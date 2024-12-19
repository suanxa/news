package com.suanxa.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.suanxa.news.api.ApiClient
import com.suanxa.news.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUsername : EditText
    private lateinit var etPassword : EditText
    private lateinit var etFullname : EditText
    private lateinit var etEmail : EditText
    private lateinit var btnSignUp : Button
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etFullname = findViewById(R.id.etFullname)
        etEmail = findViewById(R.id.etEmail)
        btnSignUp = findViewById(R.id.btnSignUp)
        progressBar = findViewById(R.id.progressBar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnSignUp.setOnClickListener{
            prosesRegister()
        }
    }

    //Method Process Register
    fun prosesRegister(){
        progressBar.visibility = View.VISIBLE
       ApiClient.apiService.register(
           etUsername.text.toString(),
           etEmail.text.toString(),
           etPassword.text.toString(),
           etFullname.text.toString()
       ).enqueue(object: Callback<RegisterResponse>{
           override fun onResponse(
               call: Call<RegisterResponse>,
               response: Response<RegisterResponse>
           ) {
               //Response Berhasil
               if (response.isSuccessful){
                   //Jika Berhasil Menambahkan Data User
                   //Kondisi True => success
                   if (response.body()!!.succes){
                       //Arahkan ke LoginActivity
                       startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                       //Pesan
                       Toast.makeText(this@RegisterActivity,response.body()!!.message,
                           Toast.LENGTH_SHORT).show()
                   }//Kondisi False => Failed
                   else{
                       //Pesan
                       Toast.makeText(this@RegisterActivity,response.body()!!.message,
                           Toast.LENGTH_SHORT).show()
                   }
               }
               progressBar.visibility = View.GONE
           }

           override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
               //Response Gagal
               Toast.makeText(this@RegisterActivity,t.message,
                   Toast.LENGTH_SHORT).show()
               progressBar.visibility = View.GONE
           }
       })
    }
}