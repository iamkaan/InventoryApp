package com.iamkaan.inventory2.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iamkaan.inventory2.R
import com.iamkaan.inventory2.auth.AuthenticationManager

class SettingsFragment : Fragment() {

    lateinit var authManager: AuthenticationManager

    lateinit var signInInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        signInInfo = root.findViewById(R.id.sign_in_info)
        val signInButton: View = root.findViewById(R.id.google_sign_in)

        authManager = AuthenticationManager(activity!!)
        updateSignInInfo()

        signInButton.setOnClickListener {
            authManager.signInWithGoogle(this) {
                updateSignInInfo()
            }
        }

        return root
    }

    private fun updateSignInInfo() {
        if (authManager.isSignedIn()) {
            val user = authManager.signedInUser()
            if (user!!.isAnonymous) {
                signInInfo.text = "You are signed in anonymously"
            } else {
                signInInfo.text = "You are signed in as ${user.displayName}"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        authManager.onActivityResult(requestCode, resultCode, data)
    }
}