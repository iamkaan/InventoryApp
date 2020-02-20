package com.iamkaan.inventory2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iamkaan.inventory2.data.InventoryDataManager
import com.iamkaan.inventory2.model.InventoryItem

class HomeViewModel : ViewModel() {

    val dataManager = InventoryDataManager()

    private val _list = MutableLiveData<List<InventoryItem>>().apply {
//        value = listOf(InventoryItem("Cheese", 3), InventoryItem("Milk", 1))
        dataManager.subscribe { value = it }
    }
    val list: LiveData<List<InventoryItem>> = _list
}