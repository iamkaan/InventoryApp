package com.iamkaan.inventory2.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.iamkaan.inventory2.model.InventoryItem

class InventoryDataManager {

    val db = FirebaseFirestore.getInstance()

    fun subscribe(listener: (list: List<InventoryItem>) -> Unit) {
        db.collection("users").document("user_id").collection("items")
            .addSnapshotListener { s: QuerySnapshot?, _: FirebaseFirestoreException? ->
                s?.apply {
                    listener(this.toObjects(InventoryItem::class.java))
                }
            }
    }

    fun add(item: InventoryItem, onComplete: () -> Unit) {
        db.collection("users").document("user_id").collection("items")
            .add(item)
            .addOnCompleteListener {
                onComplete()
            }
    }
}