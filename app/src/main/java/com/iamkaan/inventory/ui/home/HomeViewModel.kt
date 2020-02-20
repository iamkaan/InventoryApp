package com.iamkaan.inventory.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iamkaan.inventory.model.InventoryItem

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<List<InventoryItem>>().apply {
        value = listOf(InventoryItem("Cheese"), InventoryItem("Milk"), InventoryItem("Yogurt"))
    }
    val items: LiveData<List<InventoryItem>> = _text
}