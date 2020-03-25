package com.redbox.survey.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redbox.survey.R
import com.redbox.survey.domain.QuestionRepository
import com.redbox.survey.presentation.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QuestionRepository.setTest(assets.list("").asList())
        if (supportFragmentManager.findFragmentByTag("main") == null) {
            supportFragmentManager.beginTransaction().replace(
                    R.id.fragment,
                HomeFragment()
                ).addToBackStack(null).commit()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount == 0){
            finish()
        }
    }
}
