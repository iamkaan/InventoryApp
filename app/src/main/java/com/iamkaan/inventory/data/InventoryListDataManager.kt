package com.iamkaan.inventory.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.iamkaan.inventory.model.InventoryItem

class InventoryListDataManager {

    private val db = FirebaseFirestore.getInstance()

    fun subscribeForUpdates(listener: (list: List<InventoryItem>) -> Unit) {
        db.collection("inventory").document("skip_layer").collection("users").document("user_id")
            .collection("items")
            .addSnapshotListener { snapshot: QuerySnapshot?, _: FirebaseFirestoreException? ->
                snapshot?.apply { listener(documents.map { it.toObject(InventoryItem::class.java)!! }) }
            }
    }
}