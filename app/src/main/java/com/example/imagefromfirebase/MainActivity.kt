package com.example.imagefromfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.api.load
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import io.github.rosariopfernandes.firecoil.load
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView= findViewById<ImageView>(R.id.imageView)
        val storage = Firebase.storage
        val storageRef =storage.reference
        val pathRef =storageRef.child("vs.jpg")
        val urlRef = storage.getReferenceFromUrl("gs://imagedownload-b2c53.appspot.com/vs.jpg")

        // var storageRef = storage.reference
       imageView.load(pathRef)



        //var pathRef  = storageRef.child("image/vs.jpg")

    }
}

