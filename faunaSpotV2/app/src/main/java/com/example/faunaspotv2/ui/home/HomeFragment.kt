package com.example.faunaspotv2.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faunaspotv2.R
import com.example.faunaspotv2.data.Spot
import com.example.faunaspotv2.ui.main.HomeViewAdapter
import com.example.faunaspotv2.ui.spotDetail.SpotDetailActivity

class HomeFragment: Fragment() {
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.home_fragment, container, false)
        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spotRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = HomeViewAdapter(homeViewModel.getApplication(), itemClick = { spot ->

            /*val spotDetail = SpotDetailFragment().newSpotDetailInstance(spot)

            val appCompat: AppCompatActivity = (context as AppCompatActivity?)!!

            if (spotDetail != null) {
                appCompat.supportFragmentManager.beginTransaction().replace(R.id.container, spotDetail)
                    .commitNow()
            }*/

            val intent = Intent(context, SpotDetailActivity::class.java)

            intent.putExtra("suspectedAn", spot.suspectedAnimal)
            intent.putExtra("animalType", spot.animalType)
            intent.putExtra("longitude", spot.longitude)
            intent.putExtra("latidude", spot.latitude)
            intent.putExtra("what3words", spot.what3words)

            requireContext().startActivity(intent)

        })
        spotRecyclerView.adapter = adapter
        spotRecyclerView.layoutManager = LinearLayoutManager(homeViewModel.getApplication())

        homeViewModel.allSpots.observe(viewLifecycleOwner, Observer { spots ->
            spots?.let { adapter.setSpots(it)}
        })
    }
}

private fun Parcelable.putExtra(s: String, spot: Spot) {

}
