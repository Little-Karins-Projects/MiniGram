package com.thelittlekarinprojects.minigram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {

    // Firebase instance
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Gets the textboxes
        var userText = findViewById<EditText>(R.id.emailTextField)
        var passwordText = findViewById<EditText>(R.id.passwordTextField)
        var signupButton = findViewById<Button>(R.id.signUpButton)

        // Gets firebase instance
        fAuth = FirebaseAuth.getInstance()

        signupButton.setOnClickListener {
            fAuth.createUserWithEmailAndPassword(userText.text.toString(), passwordText.text.toString())
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    // Created user
                    Log.d("Register", "createUserWithEmail:success")
                    /*Toast.makeText(this, "Registration succeded!.",
                        Toast.LENGTH_SHORT).show()*/
                    Snackbar.make(this.findViewById(android.R.id.content), R.string.registration_success,Snackbar.LENGTH_SHORT).show()
                    val user  = fAuth.currentUser
                    finish() // Ends this activity and returns to previous one
                } else {

                    // If sign up fails, display a message to the user
                    Log.w("Register", "createUserWithEmail:failure", task.exception)
                    Snackbar.make(this.findViewById(android.R.id.content), R.string.registration_failure,Snackbar.LENGTH_SHORT).show()

                    /*Toast.makeText(this, "Registration failed.",
                        Toast.LENGTH_SHORT).show()*/
                }
                }
            }

    }

}
