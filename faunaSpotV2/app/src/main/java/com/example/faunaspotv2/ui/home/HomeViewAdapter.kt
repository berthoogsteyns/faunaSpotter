package com.example.faunaspotv2.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.faunaspotv2.R
import com.example.faunaspotv2.data.Spot

class HomeViewAdapter internal constructor(
    context: Context,
    private var itemClick: ((Spot) -> Unit)?
): RecyclerView.Adapter<HomeViewAdapter.SpotViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var spots = emptyList<Spot>()
    inner class SpotViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        /*val layout: ConstraintLayout = itemView.findViewById(R.id.);*/

        val animalType: TextView = itemView.findViewById(R.id.animalType)
        val what3wordsText: TextView = itemView.findViewById(R.id.what3words)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return SpotViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpotViewHolder, position: Int) {
        val current = spots[position]
        holder.animalType.text = current.animalType
        holder.what3wordsText.text = current.what3words

        holder.itemView.setOnClickListener{ itemClick?.invoke(current)}
    }

    internal fun setSpots(spots: List<Spot>) {
        this.spots = spots
        notifyDataSetChanged()
    }

    override fun getItemCount() = spots.size
}