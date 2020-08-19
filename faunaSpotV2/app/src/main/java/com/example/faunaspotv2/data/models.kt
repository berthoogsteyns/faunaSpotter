package com.example.faunaspotv2.data


import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "spots")
data class Spot(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val longitude: String,
        val latitude: String,
        val suspectedAnimal: String,
        val animalType:String,
        var what3words: String?,
        val photoUri: String?
)
