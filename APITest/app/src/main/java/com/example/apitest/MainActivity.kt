package com.example.apitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val path = "https://my-json-server.typicode.com/lenamarsilius/verbose-guacamole/hytter/"
        val gson = Gson()

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.result)
        val editText = findViewById<EditText>(R.id.input)

        button.setOnClickListener {
            runBlocking {
                try {
                    val response = gson.fromJson(Fuel.get(path+editText.text).awaitString(), Hytte::class.java)
                    Log.d("API fetching", response.name.toString())
                    textView.text = "Name: " + response.name.toString() +
                                    "\n\nLatitude: " + response.DDLat.toString() +
                                    "\n\nLongitude: " + response.DDLon.toString()
                } catch(exception: Exception) {
                    println("A network request exception was thrown: ${exception.message}")
                }
            }
        }


    }
}

// result generated from /json
data class Hytte(val id: Number?, val name: String?, val DDLat: Number?, val DDLon: Number?)