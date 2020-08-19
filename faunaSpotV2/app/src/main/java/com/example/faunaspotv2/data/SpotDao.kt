package com.example.faunaspotv2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpotDao {
    @Query("select * from spots")
    fun getSpots(): LiveData<List<Spot>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spot: Spot)
}