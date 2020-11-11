package com.eightsquare.eremitsampleapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.mtaeremit.EremitSdk
import com.library.eightsquarei.model.EnvType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.startButton)
        button.setOnClickListener {
            EremitSdk.Builder().apiKey("api_key_here").envType(EnvType.SIT).build().start(this)
        }
    }
}