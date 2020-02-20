package com.iamkaan.inventory.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iamkaan.inventory.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val adapter = InventoryAdapter(emptyList())
        root.findViewById<RecyclerView>(R.id.inventory_list).apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        homeViewModel.items.observe(this, Observer {
            adapter.update(it)
        })

        return root
    }
}