package com.example.imagefromfirebase.videoPlayerRecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.imagefromfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VideoPlayerRecyclerViewActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<VideoPlayer_card> videoPlayerCardArrayList;
    VideoPlayerRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player_recycler_view);

        setUpFirebase();


        loadDataFromFirebase();
        setUpRecyclerView();
    }

    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
    }


    private void setUpRecyclerView() {
        mRecyclerView =findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void loadDataFromFirebase() {
        if (videoPlayerCardArrayList.size() > 0) {
            videoPlayerCardArrayList.clear();
        }

        db.document("/Army Institute Of Technology/Events/PACE/Event Details")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                               if (task.isSuccessful()) {
                                                   DocumentSnapshot document = task.getResult();
                                                   if (document.exists()) {
                                                       long size = document.getLong("Names.0");
                                                       int i = 1;
                                                       while (size > 0) {

                                                       }
                                                   } else {
                                                       Log.d("Error", "No such document");
                                                   }
                                               } else {
                                                   Log.d("Error", "get failed with ", task.getException());
                                               }
                                               adapter = new VideoPlayerRecyclerViewAdapter(VideoPlayerRecyclerViewActivity.this, videoPlayerCardArrayList);
                                               mRecyclerView.setAdapter(adapter);
                                           }

                                       }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VideoPlayerRecyclerViewActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
