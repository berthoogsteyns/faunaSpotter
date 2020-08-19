package com.example.faunaspotv2.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy

class Repository(private val spotDao: SpotDao) {

    val allSpots = spotDao.getSpots()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(spot:Spot) {
        spotDao.insert(spot)
    }
}