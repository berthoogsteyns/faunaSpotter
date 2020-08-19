package com.example.faunaspotv2.ui.home

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Database
import com.example.faunaspotv2.data.AppDb
import com.example.faunaspotv2.data.Repository
import com.example.faunaspotv2.data.Spot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app){

    private val repo: Repository

    val allSpots: LiveData<List<Spot>>

    init{
        val spotDao = AppDb.getDatabase(app, viewModelScope).spotDao()
        repo = Repository(spotDao)
        allSpots = repo.allSpots
    }

    fun insert(spot:Spot) = viewModelScope.launch (Dispatchers.IO) {
        repo.insert(spot)
    }
}