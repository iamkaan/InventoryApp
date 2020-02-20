package com.iamkaan.inventory2.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthenticationManager {

    val auth = FirebaseAuth.getInstance()
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("8884708575-ct78g3njp7g55nsjg4t783a2stq39b65.apps.googleusercontent.com")
        .requestEmail()
        .build()
    private var onComplete: (currentUser: FirebaseUser) -> Unit = {}

    fun isSignedIn() = auth.currentUser != null

    fun signedInUser() = auth.currentUser

    fun signInAnonymously(activity: Activity, onComplete: (currentUser: FirebaseUser) -> Unit) {
        this.onComplete = onComplete
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(auth.currentUser!!)
                } else {
                    Toast.makeText(
                        activity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun signInWithGoogle(fragment: Fragment, onComplete: (currentUser: FirebaseUser) -> Unit) {
        this.onComplete = onComplete
        val googleSignInClient = GoogleSignIn.getClient(fragment.context!!, gso)
        fragment.startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }

    fun onActivityResult(context: Context, requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(context, account!!)
            } catch (e: ApiException) {
                Toast.makeText(
                    context, "Google authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(context: Context, acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(auth.currentUser!!)
                } else {
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    task.exception?.printStackTrace()
                }
            }
    }

    companion object {
        const val RC_SIGN_IN = 13
    }
}
