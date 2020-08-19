package com.example.faunaspotv2.ui.spotAdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.faunaspotv2.R
import com.example.faunaspotv2.data.Spot
import kotlin.random.Random


/*
class SpotAddFragment: Fragment() {
    private lateinit var spotAddViewModel: SpotAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        */
/*spotAddViewModel = ViewModelProvider(this).get(spotAddViewModel::class.java)*//*

        val root = inflater.inflate(R.layout.fragment_add_spot, container, false)

        */
/*val animalType: TextView = root.findViewById(R.id.animalType)
        val what3wordsText: TextView = root.findViewById(R.id.what3words)
*//*

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spotAddViewModel = ViewModelProvider(this).get(spotAddViewModel::class.java)
    }
}*/


class SpotAddFragment : Fragment() {
    private lateinit var spotAddViewModel: SpotAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        spotAddViewModel =
            ViewModelProvider(this).get(SpotAddViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_spot, container, false)
        /* val textView: TextView = root.findViewById(R.id.animalType)
         spotAddViewModel.text.observe(viewLifecycleOwner, Observer {
             textView.text = it
         })*/


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button_save)

        val animalType = view.findViewById<EditText>(R.id.animalType)
        val suspectedAnimal = view.findViewById<EditText>(R.id.suspectedAnimal)
        val longitude = view.findViewById<EditText>(R.id.longtitude)
        val latidude = view.findViewById<EditText>(R.id.latidude)

        button.setOnClickListener {
            spotAddViewModel.insert(
                Spot(
                    Random(1000).nextLong(),
                    longitude = longitude.text.toString(),
                    latitude = latidude.text.toString(),
                    animalType = animalType.text.toString(),
                    suspectedAnimal = suspectedAnimal.text.toString(),
                    what3words = "",
                    photoUri = ""
                )
            )
        }

    }
}