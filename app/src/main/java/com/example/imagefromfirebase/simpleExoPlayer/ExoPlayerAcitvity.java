 package com.example.imagefromfirebase.simpleExoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

 public class ExoPlayerAcitvity extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;

     FirebaseStorage storage = FirebaseStorage.getInstance();
     StorageReference storageRef = storage.getReference();
     StorageReference pathReference = storageRef.child("1AdvancedKotlinIntro.mp4");


     String videoURL;
     TextView uri;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_exo_player);
        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exo_player_view);
        getUrl();
    }

     private void getUrl() {
         uri =findViewById(R.id.uri);
         pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
         {
             @Override
             public void onSuccess(Uri downloadUrl)
             {
                 videoURL =downloadUrl.toString();
                 Log.d("====>",videoURL);
                 uri.setText(videoURL);
                 try {
                     setupVideoPlayer();

                 }catch (Exception e){
                     Log.e("ExoPlayerActivity", "exoplayer error"+ e.toString());
                 }

             }
         });

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
         exoPlayer.prepare(mediaSource);
         exoPlayer.setPlayWhenReady(true);
     }
 }
