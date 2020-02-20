package com.iamkaan.inventory2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.iamkaan.inventory2.R
import com.iamkaan.inventory2.data.InventoryDataManager
import com.iamkaan.inventory2.model.InventoryItem

class AddFragment : Fragment() {

    val dataManager = InventoryDataManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add, container, false)

        val title: EditText = root.findViewById(R.id.title)
        val quantity: EditText = root.findViewById(R.id.quantity)

        root.findViewById<Button>(R.id.save_button).setOnClickListener {
            val item = InventoryItem(title.text.toString(), quantity.text.toString().toInt())
            dataManager.add(item) {
                fragmentManager?.beginTransaction()?.remove(this)?.commit()
            }
        }

        return root
    }

    companion object {
        const val TAG = "AddFragment"
    }
}