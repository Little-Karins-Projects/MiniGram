package com.littleloot.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        fAuth = FirebaseAuth.getInstance()
        var userLogged = findViewById<TextView>(R.id.profile_mail_)
        userLogged.text = fAuth.currentUser?.email

        var signOutButton = findViewById<Button>(R.id.sign_out)

        signOutButton.setOnClickListener() {
            fAuth.signOut()
            finish()
        }
    }
}
