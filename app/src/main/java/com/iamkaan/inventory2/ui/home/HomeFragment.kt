package com.iamkaan.inventory2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.iamkaan.inventory2.R

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

        val adapter = InventoryAdapter()
        root.findViewById<RecyclerView>(R.id.inventory_list).apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        homeViewModel.list.observe(this, Observer {
            adapter.update(it)
        })

        root.findViewById<FloatingActionButton>(R.id.add_button).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.add_fragment_container, AddFragment())
                ?.addToBackStack(AddFragment.TAG)
                ?.commit()
        }

        return root
    }
}