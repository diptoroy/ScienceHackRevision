package com.example.sciencehack.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sciencehack.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class JounalDetailsActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar journalProgressBar;

    Toolbar toolbar_j_details;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jounal_details);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        webView = findViewById(R.id.webview);
        journalProgressBar = findViewById(R.id.jou_progressbar);

        toolbar_j_details = findViewById(R.id.toolbar_j_details);
        toolbar_j_details.setTitle("Article");
        setSupportActionBar(toolbar_j_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            journalProgressBar.setVisibility(View.VISIBLE);
            String url = bundle.getString("url");
            //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
            webView.loadUrl(url);
            journalProgressBar.setVisibility(View.INVISIBLE);
        }


    }
}
