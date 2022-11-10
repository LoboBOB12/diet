package com.noexerciseweightlosst.wqxw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl("https://www.facebook.com/DietBuddy-2012199758999002/");
    }
}
