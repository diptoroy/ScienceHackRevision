package com.example.sciencehack.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sciencehack.AdsModel;
import com.example.sciencehack.Model.ValueModel;
import com.example.sciencehack.R;
import com.example.sciencehack.ViewHolder.ValueHolder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ValueActivity extends AppCompatActivity {

    private RecyclerView valuerecyclerview;
    private ArrayList<Object> valueList;
    FirebaseDatabase valueDatabase;
    DatabaseReference valueRef;
    private ValueHolder valueHolder;

    Toolbar value_toolbar;

    public static final int ITEM_PER_ADS = 4;
    public static final String ADS = AdsModel.BANNER_ADS;

    private InterstitialAd valueInterstitialAd;

    ProgressBar va_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);

        value_toolbar = findViewById(R.id.toolbar_value);
        value_toolbar.setTitle("Simple Values");
        setSupportActionBar(value_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        va_progress = findViewById(R.id.va_progressBar);
        va_progress.setVisibility(View.VISIBLE);

        valuerecyclerview = findViewById(R.id.value_recyclerView_id);
        valueList = new ArrayList<>();
        valuerecyclerview.setHasFixedSize(true);
        valuerecyclerview.setLayoutManager(new LinearLayoutManager(this));
        MobileAds.initialize(this,AdsModel.APP_ID);
        valueDatabase = FirebaseDatabase.getInstance();
        valueRef = valueDatabase.getReference("Values");


        valueInterstitialAd = new InterstitialAd(this);
        valueInterstitialAd.setAdUnitId(AdsModel.INTERSTITIAL_ADS);
        valueInterstitialAd.loadAd(new AdRequest.Builder().build());

        valueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot value : dataSnapshot.getChildren()){
                    ValueModel v = value.getValue(ValueModel.class);
                    valueList.add(v);
                }
                Item();
                getBannerAds();
                loadBannerAds();
                valueHolder = new ValueHolder(getApplicationContext(),valueList);
                valueHolder.notifyDataSetChanged();
                valuerecyclerview.setAdapter(valueHolder);
                va_progress.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Item(){
        List<ValueModel> vcmodel = new ArrayList<>();

        for (ValueModel rc : vcmodel){
            valueList.add(rc);
        }
    }

    private void getBannerAds(){
        for (int i = 3; i<valueList.size(); i+= ITEM_PER_ADS){
            final AdView adView = new AdView(ValueActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(ADS);
            valueList.add(i,adView);
        }
    }

    private void loadBannerAds(){
        for (int i = 3; i<valueList.size(); i++){
            Object item = valueList.get(i);
            if (item instanceof AdView){
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (valueInterstitialAd.isLoaded()){
            valueInterstitialAd.show();
            valueInterstitialAd.setAdListener(new AdListener(){
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
