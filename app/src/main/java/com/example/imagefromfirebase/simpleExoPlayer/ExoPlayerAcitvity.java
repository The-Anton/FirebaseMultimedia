 package com.example.imagefromfirebase.simpleExoPlayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imagefromfirebase.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

 public class ExoPlayerAcitvity extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;

     FirebaseStorage storage = FirebaseStorage.getInstance();
     StorageReference storageRef = storage.getReference();
     StorageReference pathReference = storageRef.child("1AdvancedKotlinIntro.mp4");
     private boolean playWhenReady = true;
     private int currentWindow = 0;
     private long playbackPosition = 0;


     String videoURL;
     TextView uri;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_exo_player);
        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exo_player_view);
        //getUrl();
    }

     private void getUrl() {
         pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
         {
             @Override
             public void onSuccess(Uri downloadUrl)
             {
                 videoURL =downloadUrl.toString();
                 Log.d("====>",videoURL);
                 try {
                     setupVideoPlayer();

                 }catch (Exception e){
                     Log.e("ExoPlayerActivity", "exoplayer error"+ e.toString());
                 }

             }
         });

     }

     @Override
     protected void onPostResume() {
         super.onPostResume();
     }

     @Override
     protected void onStart() {
         super.onStart();
         if (Util.SDK_INT >= 24) {
             getUrl();
         }
     }

     @Override
     protected void onPause() {
         super.onPause();
         if (Util.SDK_INT >= 24) {
             releasePlayer();
         }
     }

     @Override
     protected void onStop() {
         super.onStop();
         if (Util.SDK_INT >= 24) {
             releasePlayer();
         }

     }



     private void releasePlayer() {
         if (exoPlayer != null) {
             playWhenReady = exoPlayer.getPlayWhenReady();
             playbackPosition = exoPlayer.getCurrentPosition();
             currentWindow = exoPlayer.getCurrentWindowIndex();
             exoPlayer.release();
             exoPlayer = null;
         }
     }

     private void setupVideoPlayer() {

         BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
         TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
         exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);




         Uri videoURI = Uri.parse(videoURL);
         DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_view");
         ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
         MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null , null);



         exoPlayerView.setPlayer(exoPlayer);
         exoPlayer.setPlayWhenReady(playWhenReady);
         exoPlayer.seekTo(currentWindow, playbackPosition);
         exoPlayer.prepare(mediaSource, false, false);
         exoPlayer.setPlayWhenReady(true);
     }




 }
