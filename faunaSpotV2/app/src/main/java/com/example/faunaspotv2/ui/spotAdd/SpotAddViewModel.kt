package com.example.faunaspotv2.ui.spotAdd

import android.app.Application
import androidx.lifecycle.*
import com.example.faunaspotv2.data.AppDb
import com.example.faunaspotv2.data.Repository
import com.example.faunaspotv2.data.Spot
import com.example.faunaspotv2.ui.home.HomeFragment
import com.example.faunaspotv2.ui.home.HomeViewModel
import com.what3words.androidwrapper.What3WordsV3
import com.what3words.javawrapper.request.Coordinates
import com.what3words.javawrapper.response.ConvertTo3WA
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
class SpotAddViewModel(app:Application): AndroidViewModel(app) {
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
}*/


class SpotAddViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: Repository

    private lateinit var appContext: Application

    val allSpots: LiveData<List<Spot>>

    init{
        val spotDao = AppDb.getDatabase(app, viewModelScope).spotDao()
        repo = Repository(spotDao)
        allSpots = repo.allSpots
        appContext = app

    }

    fun getWhat3Words(longI: String, lat: String): String? {
        val api =
            What3WordsV3("OPKVVVB0", appContext)

        val longitude = longI.toDouble()

        val latidude = lat.toDouble()

        return api.convertTo3wa(Coordinates(longI.toDouble(), lat.toDouble())).language("en").execute().words
    }

    fun insert(spot: Spot) = viewModelScope.launch (Dispatchers.IO) {
        spot.what3words = getWhat3Words(spot.longitude, spot.latitude)
        repo.insert(spot)
    }


}