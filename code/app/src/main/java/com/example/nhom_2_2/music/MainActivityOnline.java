package com.example.nhom_2_2.music;
/**
 * Created by phong on 12/5/2016.
 */
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;


public class MainActivityOnline extends AppCompatActivity {
    Button btnMp4;
    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video);
        AnhXa();
        btnMp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://zmp3-mp3-mv1-tr.zadn.vn/7ef9a7ef99aa70f429bb/549703781830784459?key=g1A7nyfHiU0m2N5PYBco3g&expires=1500452186");
                videoView.setVideoURI(uri);
                videoView.setMediaController(new MediaController(MainActivityOnline.this));
                videoView.start();
            }
        });

    }

    private void  AnhXa()
    {
        btnMp4 = (Button) findViewById(R.id.btnmp4);
        videoView = (VideoView) findViewById(R.id.videoView2);

    }

}
