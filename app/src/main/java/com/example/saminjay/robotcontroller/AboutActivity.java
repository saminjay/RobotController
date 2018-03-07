package com.example.saminjay.robotcontroller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by saminjay on 15/4/17.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }


    public void fb(View view) {
        Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Saminjay"));
        startActivity(fbIntent);
    }

    public void tweet(View view) {
        Intent tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Saminjay15"));
        startActivity(tweetIntent);
    }

    public void github(View view) {
        Intent gitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/saminjay"));
        startActivity(gitIntent);
    }
}
