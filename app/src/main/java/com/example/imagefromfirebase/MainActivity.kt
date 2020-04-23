package com.example.imagefromfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import coil.api.load
import com.example.imagefromfirebase.simpleExoPlayer.ExoPlayerAcitvity
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

        imageView.load(pathRef)

        val startVideoPlayer_btn =  findViewById<Button>(R.id.btn_startPlayer)

        startVideoPlayer_btn.setOnClickListener {
            startActivity(Intent(this, ExoPlayerAcitvity::class.java));

        }




    }
}

