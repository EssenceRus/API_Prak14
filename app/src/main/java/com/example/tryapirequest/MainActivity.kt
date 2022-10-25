package com.example.tryapirequest


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var tvMain: TextView
    private lateinit var button: Button
    private lateinit var button2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvMain = findViewById(R.id.tvMain)
        button = findViewById(R.id.btn)
        button2 = findViewById(R.id.button3)
        button.setOnClickListener() {
            val intent = Intent(this, InsultActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener(){
            val intent = Intent(this, QuotesActivity::class.java)
            startActivity(intent)
        }

    }
}