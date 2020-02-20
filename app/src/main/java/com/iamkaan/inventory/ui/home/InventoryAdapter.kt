package com.iamkaan.inventory.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iamkaan.inventory.R
import com.iamkaan.inventory.model.InventoryItem

class InventoryAdapter(private var list: List<InventoryItem>) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    fun update(list: List<InventoryItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory_list, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
    }

    override fun getItemCount() = list.size

    class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val title: TextView = rootView.findViewById(R.id.title)
    }
}