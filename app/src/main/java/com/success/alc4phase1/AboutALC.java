package com.success.alc4phase1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AboutALC extends AppCompatActivity {
    private WebView aboutWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_a_l_c);

        aboutWeb = findViewById(R.id.web_view);
        aboutWeb.loadUrl("https://andela.com/alc/");

    }
}
