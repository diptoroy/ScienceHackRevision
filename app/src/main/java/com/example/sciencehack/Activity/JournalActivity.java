package com.example.sciencehack.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sciencehack.AdsModel;
import com.example.sciencehack.Extra.JournalData;
import com.example.sciencehack.Interface.JournalSyncResponse;
import com.example.sciencehack.Model.JournalModel;
import com.example.sciencehack.R;
import com.example.sciencehack.ViewHolder.JournalHolder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JournalActivity extends AppCompatActivity {

    private RecyclerView jRecyclerView;
    private JournalHolder journalHolder;
    private ProgressBar j_proress;

    Toolbar toolbar_quotes;
    private InterstitialAd jInterstitialAd;
    private InterstitialAd jbInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        toolbar_quotes = findViewById(R.id.toolbar_journal);
        toolbar_quotes.setTitle("Article");
        setSupportActionBar(toolbar_quotes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        j_proress = findViewById(R.id.j_progress);
        j_proress.setVisibility(View.VISIBLE);

//        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

//        jInterstitialAd = new InterstitialAd(this);
//        jInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        jInterstitialAd.loadAd(new AdRequest.Builder().build());

        new JournalData().getJournal(new JournalSyncResponse() {
            @Override
            public void processFinish(final ArrayList<JournalModel> jList) {

                j_proress.setVisibility(View.INVISIBLE);
                jRecyclerView = findViewById(R.id.journal_recyclerview);
                journalHolder = new JournalHolder(jList,getApplicationContext());
                jRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                journalHolder.notifyDataSetChanged();
                jRecyclerView.setAdapter(journalHolder);
                journalHolder.setOnClickListener(new JournalHolder.OnItemClickListner() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(getApplicationContext(),"Position : "+position,Toast.LENGTH_SHORT).show();

                        JournalModel journalModel = jList.get(position);
                        Intent intent = new Intent(getApplicationContext(),JounalDetailsActivity.class);
                        intent.putExtra("url",journalModel.getUrl());
                        startActivity(intent);
                    }
                });
            }
        });

//        jInterstitialAd.setAdListener(new AdListener(){
//            @Override
//            public void onAdLoaded() {
//                diplayAds();
//            }
//        });

//        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (jInterstitialAd.isLoaded()){
//                            jInterstitialAd.show();
//                        }else{
//
//                        }
//                        PrepareAds();
//                    }
//                });
//            }
//        },10,25, TimeUnit.SECONDS);

        PrepareAds();
    }

    public void PrepareAds(){
        MobileAds.initialize(this, AdsModel.APP_ID);

        jbInterstitialAd = new InterstitialAd(this);
        jbInterstitialAd.setAdUnitId(AdsModel.INTERSTITIAL_ADS);
        jbInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

//    public void diplayAds(){
//        if (jInterstitialAd.isLoaded()){
//            jInterstitialAd.show();
//        }
//    }

    @Override
    public void onBackPressed() {

        if (jbInterstitialAd.isLoaded()){
            jbInterstitialAd.show();
            jbInterstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            super.onBackPressed();
        }

    }
}
