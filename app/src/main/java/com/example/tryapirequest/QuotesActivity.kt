package com.example.tryapirequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.text.Typography.quote

class QuotesActivity : AppCompatActivity() {
    private lateinit var tvMain: TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        tvMain = findViewById(R.id.textView2)
        button = findViewById(R.id.button4)

        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
        button.setOnClickListener {
            tvMain.text = executorService.submit(Callable {
                httpRequest("https://favqs.com/api/qotd")
            }).get()
        }
    }
        @Throws(IOException::class)
        fun httpRequest(urlString: String):String {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            var data: Int = connection.inputStream.read()
            var str = ""
            while (data != -1){
                str += data.toChar()
                data = connection.inputStream.read()
            }
            val mainObject = JSONObject(str)
            val item = quotes(
                mainObject.getJSONObject("quote").getString("author_permalink"),
                mainObject.getJSONObject("quote").getString("body")
            )
            val quotes: String = item.author_permalink + "\n" + item.body
            return quotes
        }

    }
