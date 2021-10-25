package com.example.mys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerAdapter(
    private val names: List<String>, private val cost: List<String>,
    private val image: List<Int>
) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namesTextView: TextView? = null
        var costTextView: TextView? = null
        var imageView: ImageView? = null

        init {
            namesTextView = itemView.findViewById(R.id.names)
            costTextView = itemView.findViewById(R.id.cost)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.namesTextView?.text = names[position]
        holder.costTextView?.text = cost[position]
        holder.imageView?.setImageResource(image[position])
    }

    override fun getItemCount() = names.size
}