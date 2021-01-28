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

//            EremitSdk.Builder().apiKey("9cba3252-9eb2-4d16-98c5-e8b57412930c").envType(EnvType.SIT).build().start(this)
            EremitSdk.Builder().apiKey(getString(R.string.api_key)).envType(EnvType.SIT).build().start(this)
        }
    }
}