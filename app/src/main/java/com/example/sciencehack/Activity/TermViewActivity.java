package com.example.sciencehack.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sciencehack.AdsModel;
import com.example.sciencehack.Model.TermViewModel;
import com.example.sciencehack.R;
import com.example.sciencehack.ViewHolder.TermHolder;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.sciencehack.Model.CommonModel.term_id;

public class TermViewActivity extends AppCompatActivity{

    Toolbar toolbar;
    FirebaseDatabase tDatabase;
    DatabaseReference tReference;
    private RecyclerView recyclerView;
    private List<Object> list = new ArrayList<>();
    private TermHolder adapter;

    private List<TermViewModel> stList = new ArrayList<>();

    ProgressBar t_progress;
    public static final int ITEM_PER_ADS = 4;
    public static final String ADS = AdsModel.BANNER_ADS;

    private InterstitialAd termInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_view);

        toolbar = findViewById(R.id.toolbar_term);
        toolbar.setTitle("Formula");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.term_recyclerView_id);
        t_progress = findViewById(R.id.t_progressBar);
        t_progress.setVisibility(View.VISIBLE);

        tDatabase = FirebaseDatabase.getInstance();
        tReference = tDatabase.getReference("Term");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MobileAds.initialize(this,AdsModel.APP_ID);

        termInterstitialAd = new InterstitialAd(this);
        termInterstitialAd.setAdUnitId(AdsModel.INTERSTITIAL_ADS);
        termInterstitialAd.loadAd(new AdRequest.Builder().build());

        tReference.orderByChild("term_id").equalTo(term_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    TermViewModel term = data.getValue(TermViewModel.class);
                    list.add(term);
                }
                Item();
                getBannerAds();
                loadBannerAds();
                adapter = new TermHolder(TermViewActivity.this,list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                t_progress.setVisibility(View.INVISIBLE);
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(TermViewActivity.this,"Check your Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Item(){
        List<TermViewModel> tmodel = new ArrayList<>();

        for (TermViewModel rc : tmodel){
            list.add(rc);
        }
    }

    private void getBannerAds(){
        for (int i = 3; i<list.size(); i+= ITEM_PER_ADS){
            final AdView adView = new AdView(TermViewActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(ADS);
            list.add(i,adView);
        }
    }

    private void loadBannerAds(){
        for (int i = 3; i<list.size(); i++){
            Object item = list.get(i);
            if (item instanceof AdView){
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (termInterstitialAd.isLoaded()){
            termInterstitialAd.show();
            termInterstitialAd.setAdListener(new AdListener(){
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

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.term_search,menu);
//        final MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(this);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
//
//        String input = newText.toLowerCase();
//        List<Object> newList = new ArrayList<>();
//        for (Object term : list){
//            if (term.toString().contains(input)){
//                newList.add(term);
//            }
//
//        }
//        adapter.update(newList);
//        return true;
//    }

}
// implements SearchView.OnQueryTextListener