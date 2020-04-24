package com.example.imagefromfirebase.videoPlayerRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagefromfirebase.R;



import java.util.ArrayList;

public class VideoPlayerRecyclerViewAdapter extends RecyclerView.Adapter<VideoPlayerRecyclerViewHolder> {

    VideoPlayerRecyclerViewActivity videoPlayerRecyclerViewActivity;
    ArrayList<VideoPlayer_card> videoPlayerCardArrayList;
    int sum=0;

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

    }


    @Override
    public int getItemCount() {
        return videoPlayerCardArrayList.size();
    }



}
