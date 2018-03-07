package com.example.saminjay.robotcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView imageView = findViewById(R.id.splash);
        imageView.setImageResource(R.drawable.robodroid_bgsmall);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, ListActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    public void next(View v) {

        Intent i = new Intent(this, ListActivity.class);
        startActivity(i);
    }
}
