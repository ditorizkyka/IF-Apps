package com.ditorizkyka.bestinformaticsstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListCampusAdapter(private val listCampus: ArrayList<Campus>) : RecyclerView.Adapter<ListCampusAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgphoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvLocation: TextView = itemView.findViewById(R.id.tv_item_location)
        val tvProfile : TextView = itemView.findViewById(R.id.tv_profile)
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, profile, photo, location) = listCampus[position]
        holder.imgphoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvLocation.text = location
        holder.tvProfile.text = profile
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listCampus[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Campus)
    }

    override fun getItemCount(): Int = listCampus.size


}