package com.redbox.survey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentByTag("main") == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment, MainFragment())
                .addToBackStack("main").commit()
        }
    }
}
