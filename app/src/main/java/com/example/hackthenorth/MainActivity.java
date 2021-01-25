package com.example.hackthenorth;


import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.hackthenorth.MESSAGE";
    public static final String EXTRA_TEXT = "com.example.hackthenorth.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }
    }

    public void go_to_meme_page(View view) {
        Intent intent = new Intent(MainActivity.this, MemePageActivity.class);
        final Random myRandom = new Random();
        int rn = myRandom.nextInt(19);
        String num = String.valueOf(rn);
        intent.putExtra(EXTRA_MESSAGE, num);
        startActivity(intent);
    }

    public void go_to_media_page(View view) {
        Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
        startActivity(intent);
    }

    public void go_to_quote_page(View view) {
        Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
        final Random myRandom = new Random();
        int rn = myRandom.nextInt(8);
        String num = String.valueOf(rn);
        intent.putExtra(EXTRA_TEXT, num);
        startActivity(intent);
    }

    public void go_to_call_page(View view) {
        Intent intent = new Intent(MainActivity.this, CallActivity.class);
        startActivity(intent);
    }
}