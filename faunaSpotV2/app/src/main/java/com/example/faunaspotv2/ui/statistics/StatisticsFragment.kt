package com.example.faunaspotv2.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.faunaspotv2.R

class StatisticsFragment : Fragment(){
    private lateinit var statisticsViewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statisticsViewModel =
            ViewModelProvider(this).get(statisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.statistics_fragment, container, false)

        return root
    }
}