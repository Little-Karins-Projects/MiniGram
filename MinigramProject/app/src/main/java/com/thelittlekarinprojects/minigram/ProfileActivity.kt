package com.thelittlekarinprojects.minigram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    // Firebase instance
    private lateinit var fAuth : FirebaseAuth

    // Array of images
    var imageList = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        fAuth = FirebaseAuth.getInstance()

        createDummyData()
        var userLogged = findViewById<TextView>(R.id.profile_user_mail)
        userLogged.text = fAuth.currentUser?.email

        var signOutButton = findViewById<Button>(R.id.profile_sign_out)

        signOutButton.setOnClickListener() {
            fAuth.signOut()
            finish()
        }

        profile_recycler_view.layoutManager = GridLayoutManager(this, 3)
        profile_recycler_view.adapter = ImageAdapter(imageList)

    }

    private fun createDummyData() {

        for (i in 0..40) {
            imageList.add(Image(R.drawable.test_image))
            imageList.add(Image(R.drawable.test_image2))
            imageList.add(Image(R.drawable.test_image3))
            imageList.add(Image(R.drawable.test_image4))

        }

    }
}
