package com.thelittlekarinprojects.minigram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

        // Gets the instance
        fAuth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            fAuth.signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString())
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        // Logged user
                        Log.d("Authentication", "createUserWithEmail:success")
                        Snackbar.make(this.findViewById(android.R.id.content), R.string.authentication_success,
                            Snackbar.LENGTH_SHORT).show()

                        /*Toast.makeText(this, "Signed In!.",
                            Toast.LENGTH_SHORT).show()*/
                        val user  = fAuth.currentUser
                    } else {
                        // If sign in fails, display a message to the user
                        Log.w("Authentication", "createUserWithEmail:failure", task.exception)
                        Snackbar.make(this.findViewById(android.R.id.content), R.string.authentication_failed,Snackbar.LENGTH_SHORT).show()

                        /*Toast.makeText(this, "Wrong Credentials.",
                            Toast.LENGTH_SHORT).show()*/
                    }
                }
        }

        registerButton.setOnClickListener { val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)}
    }

    override fun onStart() {
        super.onStart()
        var currentUser = fAuth.currentUser
    }
}
