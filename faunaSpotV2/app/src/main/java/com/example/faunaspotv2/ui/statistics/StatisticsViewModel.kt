package com.example.faunaspotv2.ui.statistics

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.faunaspotv2.R
import com.example.faunaspotv2.data.AppDb
import com.example.faunaspotv2.data.Repository
import com.example.faunaspotv2.data.Spot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsViewModel(app:Application): AndroidViewModel(app){
    private val repo: Repository

    val allSpots: LiveData<List<Spot>>

    init{
        val spotDao = AppDb.getDatabase(app, viewModelScope).spotDao()
        repo = Repository(spotDao)
        allSpots = repo.allSpots
    }

    fun insert(spot: Spot) = viewModelScope.launch (Dispatchers.IO) {
        repo.insert(spot)
    }
}