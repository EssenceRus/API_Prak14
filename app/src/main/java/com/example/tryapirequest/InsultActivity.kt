package com.example.tryapirequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class InsultActivity : AppCompatActivity() {
    private lateinit var tvMain: TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insult)
        tvMain = findViewById(R.id.textView)
        button = findViewById(R.id.button2)



        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
        button.setOnClickListener {
            tvMain.text = executorService.submit(Callable {
                httpRequest("https://evilinsult.com/generate_insult.php?lang=en&type=json")
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
        val item = insult(
            mainObject.getString("insult")
        )
        val insult: String = item.insult
        return insult
        return str
    }
}