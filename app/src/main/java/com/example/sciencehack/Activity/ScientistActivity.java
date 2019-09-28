package com.example.sciencehack.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sciencehack.AdsModel;
import com.example.sciencehack.Interface.ItemClickListener;
import com.example.sciencehack.Model.CommonModel;
import com.example.sciencehack.Model.ScientistModel;
import com.example.sciencehack.R;
import com.example.sciencehack.ViewHolder.ScientistHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScientistActivity extends AppCompatActivity {
    RecyclerView scientistRecyclerView;
    StaggeredGridLayoutManager sStaggeredGridLayoutManager;
    FirebaseRecyclerAdapter<ScientistModel, ScientistHolder> sciAdapter;
    FirebaseDatabase sDatabase;
    DatabaseReference sRef;

    Toolbar toolbar_scientist;

//    private InterstitialAd sInterstitialAd;
    private InterstitialAd sbInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientist);

//        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
//
//        sInterstitialAd = new InterstitialAd(this);
//        sInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        sInterstitialAd.loadAd(new AdRequest.Builder().build());

        toolbar_scientist = findViewById(R.id.toolbar_scientist);
        toolbar_scientist.setTitle("Scientist");
        setSupportActionBar(toolbar_scientist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sDatabase = FirebaseDatabase.getInstance();
        sRef = sDatabase.getReference("Scientist");
        scientistRecyclerView = findViewById(R.id.scientist_recyclerView_id);
        //scientistRecyclerView.setHasFixedSize(false);
        sStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        scientistRecyclerView.setLayoutManager(sStaggeredGridLayoutManager);

//        sInterstitialAd.setAdListener(new AdListener(){
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
//                        if (sInterstitialAd.isLoaded()){
//                            sInterstitialAd.show();
//                        }else{
//
//                        }
//                        PrepareAds();
//                    }
//                });
//            }
//        },10,25, TimeUnit.SECONDS);

        PrepareAds();

        loadScientist();

    }

    private void loadScientist() {

        sciAdapter = new FirebaseRecyclerAdapter<ScientistModel, ScientistHolder>(
                ScientistModel.class,
                R.layout.scientist_view_row,
                ScientistHolder.class,
                sRef
        ) {
            @Override
            protected void populateViewHolder(ScientistHolder scientistHolder, ScientistModel scientistModel, int i) {
                scientistHolder.scientistName.setText(scientistModel.getsName());
                Picasso.get().load(scientistModel.getsProfile()).into(scientistHolder.scientistImage);

                scientistHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent nextSci = new Intent(getApplicationContext(), ScientistDetailsActivity.class);
                        CommonModel.scientist_id = sciAdapter.getRef(position).getKey();
                        startActivity(nextSci);
                    }
                });
            }
        };
        sciAdapter.notifyDataSetChanged();
        scientistRecyclerView.setAdapter(sciAdapter);
    }

    public void PrepareAds(){
        MobileAds.initialize(this, AdsModel.APP_ID);

        sbInterstitialAd = new InterstitialAd(this);
        sbInterstitialAd.setAdUnitId(AdsModel.INTERSTITIAL_ADS);
        sbInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

//    public void diplayAds(){
//        if (sInterstitialAd.isLoaded()){
//            sInterstitialAd.show();
//        }
//    }

    @Override
    public void onBackPressed() {

        if (sbInterstitialAd.isLoaded()){
            sbInterstitialAd.show();
            sbInterstitialAd.setAdListener(new AdListener(){
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
