package com.iamkaan.inventory.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iamkaan.inventory.data.InventoryListDataManager
import com.iamkaan.inventory.model.InventoryItem

class HomeViewModel : ViewModel() {

    private val dataManager = InventoryListDataManager()
    private val _text = MutableLiveData<List<InventoryItem>>().apply {
        dataManager.subscribeForUpdates { value = it }
    }
    val items: LiveData<List<InventoryItem>> = _text
}