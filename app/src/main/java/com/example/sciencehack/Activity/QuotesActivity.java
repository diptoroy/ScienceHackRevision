package com.example.sciencehack.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sciencehack.AdsModel;
import com.example.sciencehack.Model.QuotesModel;
import com.example.sciencehack.R;
import com.example.sciencehack.ViewHolder.QuotesHolder;
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

public class QuotesActivity extends AppCompatActivity {

    private RecyclerView qutoesRecyclerView;
    private QuotesHolder quotesHolder;
    private List<Object> qObjects = new ArrayList<>();
    FirebaseDatabase qDatabase;
    DatabaseReference qRef;

    Toolbar toolbar_quotes;

    public static final int ITEM_PER_ADS = 4;
    public static final String ADS = AdsModel.BANNER_ADS;

    private InterstitialAd quotesInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        toolbar_quotes = findViewById(R.id.toolbar_quotes);
        toolbar_quotes.setTitle("Quotes");
        setSupportActionBar(toolbar_quotes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        qutoesRecyclerView = findViewById(R.id.quotes_recyclerView_id);
        qutoesRecyclerView.setHasFixedSize(true);
        qutoesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        qDatabase = FirebaseDatabase.getInstance();
        qRef = qDatabase.getReference("Quotes");

        MobileAds.initialize(this,AdsModel.APP_ID);

        quotesInterstitialAd = new InterstitialAd(this);
        quotesInterstitialAd.setAdUnitId(AdsModel.INTERSTITIAL_ADS);
        quotesInterstitialAd.loadAd(new AdRequest.Builder().build());

        qRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot qu : dataSnapshot.getChildren()){
                    QuotesModel v = qu.getValue(QuotesModel.class);
                    qObjects.add(v);
                }
                Item();
                getBannerAds();
                loadBannerAds();
                quotesHolder = new QuotesHolder(getApplicationContext(),qObjects);
                quotesHolder.notifyDataSetChanged();
                qutoesRecyclerView.setAdapter(quotesHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Item(){
        List<QuotesModel> qmodel = new ArrayList<>();

        for (QuotesModel qc : qmodel){
            qObjects.add(qc);
        }
    }

    private void getBannerAds(){
        for (int i = 3; i<qObjects.size(); i+= ITEM_PER_ADS){
            final AdView adView = new AdView(QuotesActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(ADS);
            qObjects.add(i,adView);
        }
    }

    private void loadBannerAds(){
        for (int i = 3; i<qObjects.size(); i++){
            Object item = qObjects.get(i);
            if (item instanceof AdView){
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (quotesInterstitialAd.isLoaded()){
            quotesInterstitialAd.show();
            quotesInterstitialAd.setAdListener(new AdListener(){
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
