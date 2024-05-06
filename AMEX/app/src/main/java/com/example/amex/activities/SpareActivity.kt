package com.example.amex.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amex.databinding.ActivitySpareBinding

class SpareActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySpareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedData = intent.getStringExtra("key")
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val score = sharedPreferences.getString("testScore","")

        if(receivedData=="1"){
            startActivity(Intent(applicationContext, User2DocumentsScreenActivity::class.java))
            finish()
        }
        else if(receivedData=="0"){
            if(score=="") {
                startActivity(Intent(applicationContext, PersonalityAssessmentActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(applicationContext, HomeScreenActivity::class.java))
                finish()
            }
        }
        else{

        }



    }
}