package com.littleloot.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance() // Inicializamos la variable

        var sigButton = findViewById<Button>(R.id.sign_user_)
        var loginButton = findViewById<Button>(R.id.login_user_)
        var acceptButton = findViewById<Button>(R.id.accept_nick_)
        var textnick = findViewById<EditText>(R.id.user_nick_)
        var userEmail = findViewById<EditText>(R.id.User_email_)
        var passwordText = findViewById<EditText>(R.id.User_password_)

        sigButton.setOnClickListener {
            acceptButton.visibility = View.VISIBLE
            textnick.visibility = View.VISIBLE
            loginButton.visibility = View.GONE
            sigButton.visibility = View.GONE
        }

        loginButton.setOnClickListener {
            mAuth.signInWithEmailAndPassword(userEmail.text.toString(), passwordText.text.toString())
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        // Logged user
                        Log.d("Authentication", "logUserWithEmail:success")
                        Snackbar.make(this.findViewById(android.R.id.content), R.string.authentication_success,
                            Snackbar.LENGTH_SHORT).show()
                        val intent = Intent(this, ProfileActivity ::class.java)
                        startActivity(intent)

                        /*Toast.makeText(this, "Signed In!.",
                            Toast.LENGTH_SHORT).show()*/
                    } else {
                        // If sign in fails, display a message to the user
                        Log.w("Authentication", "logUserWithEmail:failure", task.exception)
                        Snackbar.make(this.findViewById(android.R.id.content), R.string.authentication_failed,
                            Snackbar.LENGTH_SHORT).show()

                        /*Toast.makeText(this, "Wrong Credentials.",
                            Toast.LENGTH_SHORT).show()*/
                    }
                }
        }

        acceptButton.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                userEmail.text.toString(),
                passwordText.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Created user
                        Log.d("Register", "createUserWithEmail:success")
                        /*Toast.makeText(this, "Registration succeded!.",
                        Toast.LENGTH_SHORT).show()*/
                        Snackbar.make(
                            this.findViewById(android.R.id.content),
                            R.string.registration_success,
                            Snackbar.LENGTH_SHORT
                        ).show()
                        acceptButton.visibility = View.GONE
                        textnick.visibility = View.GONE
                        loginButton.visibility = View.VISIBLE
                        sigButton.visibility = View.VISIBLE
                    } else {

                        // If sign up fails, display a message to the user
                        Log.w("Register", "createUserWithEmail:failure", task.exception)
                        Snackbar.make(
                            this.findViewById(android.R.id.content),
                            R.string.registration_failure,
                            Snackbar.LENGTH_SHORT
                        ).show()

                        /*Toast.makeText(this, "Registration failed.",
                        Toast.LENGTH_SHORT).show()*/
                    }
                }


        }

    }
}
