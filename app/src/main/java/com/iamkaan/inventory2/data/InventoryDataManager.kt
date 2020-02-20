package com.iamkaan.inventory2.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.iamkaan.inventory2.auth.AuthenticationManager
import com.iamkaan.inventory2.model.InventoryItem

class InventoryDataManager {

    val db = FirebaseFirestore.getInstance()
    val authManager = AuthenticationManager()

    fun subscribe(listener: (list: List<InventoryItem>) -> Unit) {
        if (authManager.isSignedIn()) {
            db.collection("users").document(authManager.signedInUser()!!.uid).collection("items")
                .addSnapshotListener { s: QuerySnapshot?, _: FirebaseFirestoreException? ->
                    s?.apply {
                        listener(this.toObjects(InventoryItem::class.java))
                    }
                }
        }
    }

    fun add(item: InventoryItem, onComplete: () -> Unit) {
        if (authManager.isSignedIn()) {
            db.collection("users").document(authManager.signedInUser()!!.uid).collection("items")
                .add(item)
                .addOnCompleteListener {
                    onComplete()
                }
        }
    }
}