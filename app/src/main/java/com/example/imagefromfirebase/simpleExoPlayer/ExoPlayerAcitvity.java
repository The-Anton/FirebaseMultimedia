 package com.example.imagefromfirebase.simpleExoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

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

 public class ExoPlayerAcitvity extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;
     String videoURL = "http://blueappsoftware.in/layout_design_android_blog.mp4";

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_exo_player);

        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exo_player_view);
        try {
            setupVideoPlayer();

        }catch (Exception e){
            Log.e("ExoPlayerActivity", "exoplayer error"+ e.toString());
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
         exoPlayer.prepare(mediaSource);
         exoPlayer.setPlayWhenReady(true);
     }
 }
