package com.example.hackthenorth;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioAttributes;
import android.media.MediaPlayer;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;


public class MediaPlayerActivity extends AppCompatActivity {
    Button play_button;
    Button back_button;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player);
        play_button = findViewById(R.id.play_button);
        back_button = findViewById(R.id.back_button);
        reff = FirebaseDatabase.getInstance().getReference().child("audio").child("0");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url = dataSnapshot.child("url").getValue().toString();
                MediaPlayer mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(url);
                    mPlayer.prepare();
                    mPlayer.start();
                    play_button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (mPlayer.isPlaying()) {
                                mPlayer.pause();
                                play_button.setText("Play");
                            }
                            else {
                                mPlayer.start();
                                play_button.setText("Pause");
                            }
                        }
                    });
                    back_button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            mPlayer.stop();
                            Intent intent = new Intent(MediaPlayerActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void onClick(View view) {
    }
}