package com.example.faunaspotv2.ui.spotDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faunaspotv2.R
import com.example.faunaspotv2.data.Spot


class SpotDetailFragment : Fragment() {

    private lateinit var spot:Spot

    public fun newSpotDetailInstance(spotT:Spot): SpotDetailFragment? {
        spot = spotT
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spot_detail, container, false)
    }

}