package com.example.imagefromfirebase.videoPlayerRecyclerView;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.imagefromfirebase.R;

public class VideoPlayerRecyclerViewHolder extends RecyclerView.ViewHolder {


    public   TextView video_Title;


    public VideoPlayerRecyclerViewHolder(View itemView) {
        super(itemView);

        video_Title= itemView.findViewById(R.id.video_titleText);



    }


}
