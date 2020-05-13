package com.thelittlekarinprojects.minigram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.activity_profile.*

class ImageDetailsActivity : AppCompatActivity() {

    // Firebase Auth instance
    private lateinit var fAuth : FirebaseAuth

    // List of imageDetails
     var imageDetailsList = ArrayList<ImageDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)

        fAuth = FirebaseAuth.getInstance()

        Log.i("LOG",fAuth.currentUser?.email)

        createDummyDetailsData()

        image_details_recycler_view.layoutManager = LinearLayoutManager(this)
        image_details_recycler_view.adapter = ImageDetailsAdapter(imageDetailsList)

    }

    fun createDummyDetailsData() {
        for (i in 0..40) {
            imageDetailsList.add(ImageDetails(R.drawable.test_image, "Pervy Goofy", "Welp, time to beat my meat!"))
            imageDetailsList.add(ImageDetails(R.drawable.test_image2, "Sanic the doghedge", "Yer 2 slew"))
            imageDetailsList.add(ImageDetails(R.drawable.test_image3, "Ugandan Knuckles", "Do you know da wae?"))
            imageDetailsList.add(ImageDetails(R.drawable.test_image4, "Coffin nibbas", "Stay at home or dance with us"))

        }

    }
}
