package com.example.imagefromfirebase.videoPlayerRecyclerView;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagefromfirebase.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
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
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

public class VideoPlayerRecyclerViewAdapter extends RecyclerView.Adapter<VideoPlayerRecyclerViewHolder> {

    VideoPlayerRecyclerViewActivity videoPlayerRecyclerViewActivity;
    ArrayList<VideoPlayer_card> videoPlayerCardArrayList;

    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference pathReference = storageRef.child("1AdvancedKotlinIntro.mp4");


    String videoURL;
    TextView uri;

    public VideoPlayerRecyclerViewAdapter(VideoPlayerRecyclerViewActivity videoPlayerRecyclerViewActivity, ArrayList<VideoPlayer_card> videoPlayerCardArrayList){
        this.videoPlayerRecyclerViewActivity= videoPlayerRecyclerViewActivity;
        this.videoPlayerCardArrayList = videoPlayerCardArrayList;
    }

    @NonNull
    @Override
    public VideoPlayerRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context;
        LayoutInflater layoutInflater = LayoutInflater.from(videoPlayerRecyclerViewActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.card_elements, parent,false);

        return new VideoPlayerRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoPlayerRecyclerViewHolder holder, final int position) {
        holder.video_Title.setText(videoPlayerCardArrayList.get(position).getVideo_Title());
      //  holder.prize_text_view.setText(videoPlayerCardArrayList.get(position).getVideoURL());


        videoURL= videoPlayerCardArrayList.get(position).getVideoURL();
        setupVideoPlayer();


    }



    private void setupVideoPlayer() {

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        exoPlayer = ExoPlayerFactory.newSimpleInstance((RenderersFactory) this, trackSelector);

        Uri videoURI = Uri.parse(videoURL);
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_view");
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null , null);

        exoPlayerView.setPlayer(exoPlayer);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    public int getItemCount() {
        return videoPlayerCardArrayList.size();
    }



}
