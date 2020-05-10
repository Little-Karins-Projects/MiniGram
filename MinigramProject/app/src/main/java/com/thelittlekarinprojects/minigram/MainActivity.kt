package com.thelittlekarinprojects.minigram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    // Firebase instance
    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emailText = findViewById<EditText>(R.id.emailTextFieldLogin)
        var passwordText = findViewById<EditText>(R.id.passwordTextFieldLogin)
        var loginButton = findViewById<Button>(R.id.loginButton)
        var registerButton = findViewById<Button>(R.id.registerButton)
        var userLogged = findViewById<TextView>(R.id.userLogged)

        // Gets the instance
        fAuth = FirebaseAuth.getInstance()

        if(fAuth.currentUser == null) {
            userLogged.text = "No user Logged";
        } else {
            // Go directly to profile page
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            fAuth.signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString())
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        // Logged user
                        Log.d("Authentication", "logUserWithEmail:success")
                        Snackbar.make(this.findViewById(android.R.id.content), R.string.authentication_success,
                            Snackbar.LENGTH_SHORT).show()
                        userLogged.text = fAuth.currentUser?.email
                        Log.i("User", fAuth.currentUser?.email)
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)

                        /*Toast.makeText(this, "Signed In!.",
                            Toast.LENGTH_SHORT).show()*/
                    } else {
                        // If sign in fails, display a message to the user
                        Log.w("Authentication", "logUserWithEmail:failure", task.exception)
                        Snackbar.make(this.findViewById(android.R.id.content), R.string.authentication_failed,Snackbar.LENGTH_SHORT).show()

                        /*Toast.makeText(this, "Wrong Credentials.",
                            Toast.LENGTH_SHORT).show()*/
                    }
                }
        }

        registerButton.setOnClickListener { val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)}
    }

    override fun onDestroy() {
        super.onDestroy()
        fAuth.signOut()
    }

}
