package com.example.faunaspotv2.ui.spotDetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.faunaspotv2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SpotDetailActivity : AppCompatActivity() {
    val TAG: String = "SpotDetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spotdetail)

        val backButton: FloatingActionButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(baseContext, SpotDetailActivity::class.java)
            baseContext.startActivity(intent)
        }
        getIncomingData()
    }

    private fun getIncomingData() {
        var test = intent.getStringExtra("animalType")
        test = intent.getStringExtra("suspectedAn")
        test = intent.getStringExtra("longitude")
        test = intent.getStringExtra("latidude")
        test = intent.getStringExtra("what3words")
        Log.i("type", intent.getStringExtra("animalType").toString())
        Log.i("type", intent.getStringExtra("suspectedAn").toString())
        Log.i("type", intent.getStringExtra("longitude").toString())
        Log.i("type", intent.getStringExtra("latidude").toString())
        Log.i("type", intent.getStringExtra("what3words").toString())
        if (intent.hasExtra("suspectedAn")
            && intent.hasExtra("animalType")
            && intent.hasExtra("longitude")
            && intent.hasExtra("latidude")
            ){
            val animalTypeTV: TextView = findViewById(R.id.animalType)
            val suspectedAnimalTV: TextView = findViewById(R.id.suspectedAnimal)
            val longtitudeTV: TextView = findViewById(R.id.longtitude)
            val latidudeTV: TextView = findViewById(R.id.latidude)
            val threewordsTV: TextView = findViewById(R.id.what3words)

            val test = intent.getStringExtra("animalType")


            animalTypeTV.text = intent.getStringExtra("animalType")
            suspectedAnimalTV.text = intent.getStringExtra("suspectedAnimal")
            longtitudeTV.text = intent.getStringExtra("longtitude")
            latidudeTV.text = intent.getStringExtra("latidude")
            threewordsTV.text = intent.getStringExtra("what3words")

        }

    }


}