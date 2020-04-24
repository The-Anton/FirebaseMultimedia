package com.example.imagefromfirebase.videoPlayerRecyclerView;

public class VideoPlayer_card {

    String video_Title;
    String videoURL;

    public VideoPlayer_card(String video_Title, String videoURL){
        this.video_Title= video_Title;
        this.videoURL= videoURL;
    }

    public String getVideo_Title(){
        return video_Title;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideo_Title(String video_Title) {
        this.video_Title = video_Title;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }


}
