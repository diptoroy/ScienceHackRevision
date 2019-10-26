package com.example.sciencehack.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sciencehack.Extra.ForceUpdateChecker;
import com.example.sciencehack.Model.DoYou;
import com.example.sciencehack.ViewHolder.DouHolder;
import com.example.sciencehack.Interface.ItemClickListener;
import com.example.sciencehack.Model.CommonModel;
import com.example.sciencehack.Model.FeaturesModel;
import com.example.sciencehack.Model.TermModel;
import com.example.sciencehack.R;

import com.example.sciencehack.Model.SliderModel;
import com.example.sciencehack.ViewHolder.SliderPageAdapter;
import com.example.sciencehack.ViewHolder.FeaturesHolder;
import com.example.sciencehack.ViewHolder.TermViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements ForceUpdateChecker.OnUpdateNeededListener {

    private List<SliderModel> lstSlides ;
    private ViewPager sliderpager;
    FirebaseDatabase database;
    DatabaseReference reference;
    private TabLayout indicator;

    RecyclerView termRecyclerView;
    LinearLayoutManager linearLayoutManager;
    FirebaseRecyclerAdapter<TermModel, TermViewholder> adapter;
    FirebaseDatabase termDatabase;
    DatabaseReference termReference;


    RecyclerView doRecyclerView;
    RecyclerView.LayoutManager doLayoutManager;
    FirebaseRecyclerAdapter<DoYou, DouHolder> doAdapter;
    FirebaseDatabase dDatabase;
    DatabaseReference dReference;

    RecyclerView features_recyclerview;
    RecyclerView.LayoutManager feLayoutManager;
    FirebaseRecyclerAdapter<FeaturesModel, FeaturesHolder> fAdapter;
    FirebaseDatabase fDatabase;
    DatabaseReference fReference;

    Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainToolbar = findViewById(R.id.toolbar2);
        mainToolbar.setTitle("Science Hack");
        setSupportActionBar(mainToolbar);

        sliderpager = findViewById(R.id.slider);
        lstSlides = new ArrayList<SliderModel>();
        indicator = findViewById(R.id.indcator);

        termRecyclerView = findViewById(R.id.term_recyclerView_id);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        termRecyclerView.setLayoutManager(linearLayoutManager);
        termDatabase = FirebaseDatabase.getInstance();
        termReference = termDatabase.getReference("Term_Subject");


        features_recyclerview = findViewById(R.id.features_recyclerview);
        feLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        features_recyclerview.setLayoutManager(feLayoutManager);
        fDatabase = FirebaseDatabase.getInstance();
        fReference = fDatabase.getReference("Features");


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Image");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    SliderModel sm = ds.getValue(SliderModel.class);
                    lstSlides.add(sm);

                }
                SliderPageAdapter sliderPageAdapter = new SliderPageAdapter(MainActivity.this,lstSlides);
                sliderPageAdapter.notifyDataSetChanged();
                sliderpager.setAdapter(sliderPageAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);


        doRecyclerView = findViewById(R.id.do_you_recyclerview);
        doLayoutManager = new LinearLayoutManager(this);
        doRecyclerView.setLayoutManager(doLayoutManager);
        dDatabase = FirebaseDatabase.getInstance();
        dReference = dDatabase.getReference("Do_you_know");

        loadDoYouKnow();

        loadSubject();

        loadFeatures();

        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:

                AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
                View aView = getLayoutInflater().inflate(R.layout.about_dialog,null);
                Button rate = aView.findViewById(R.id.rate);
                rate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id="+getPackageName())));
                        }catch (ActivityNotFoundException e){
                            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                        }
                    }
                });
                aBuilder.setView(aView);
                AlertDialog ab = aBuilder.create();
                ab.getWindow().getAttributes().windowAnimations = R.style.scale;
                ab.show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    private void loadFeatures() {

        fAdapter =
                new FirebaseRecyclerAdapter<FeaturesModel, FeaturesHolder>(
                        FeaturesModel.class,
                        R.layout.features_row,
                        FeaturesHolder.class,
                        fReference
                ) {
                    @Override
                    protected void populateViewHolder(FeaturesHolder featuresHolder, FeaturesModel featuresModel, int i) {

                        featuresHolder.f_text.setText(featuresModel.getFe_name());
                        Picasso.get().load(featuresModel.getFe_img()).into(featuresHolder.f_img);
                        //menu_pro.setVisibility(View.INVISIBLE);
                        featuresHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {

                                if (position == 0){
                                    //Toast.makeText(getApplicationContext(),"clicked"+position,Toast.LENGTH_SHORT).show();
                                   Intent values = new Intent(getApplicationContext(), ValueActivity.class);
                                   startActivity(values);
                                }else if (position == 1){
                                    //Toast.makeText(getApplicationContext(),"clicked"+position,Toast.LENGTH_SHORT).show();
                                    Intent quotes = new Intent(getApplicationContext(), QuotesActivity.class);
                                    startActivity(quotes);
                                }else if (position == 2){
                                    //Toast.makeText(getApplicationContext(),"clicked"+position,Toast.LENGTH_SHORT).show();
                                    Intent scientist = new Intent(getApplicationContext(), ScientistActivity.class);
                                    startActivity(scientist);
                                }else if (position == 3){
                                    //Toast.makeText(getApplicationContext(),"clicked"+position,Toast.LENGTH_SHORT).show();
                                    Intent news = new Intent(getApplicationContext(), JournalActivity.class);
                                    startActivity(news);
                                }else if (position == 4){
                                    //Toast.makeText(getApplicationContext(),"clicked"+position,Toast.LENGTH_SHORT).show();
                                    Intent news = new Intent(getApplicationContext(), ContactActivity.class);
                                    startActivity(news);
                                }

                            }
                        });

                    }
                };

        fAdapter.notifyDataSetChanged();
        features_recyclerview.setAdapter(fAdapter);
    }

    private void loadDoYouKnow() {

        doAdapter = new FirebaseRecyclerAdapter<DoYou, DouHolder>(
                DoYou.class,
                R.layout.do_you_know,
                DouHolder.class,
                dReference
        ) {
            @Override
            protected void populateViewHolder(DouHolder douHolder, DoYou doYou, int i) {
                douHolder.do_title.setText(doYou.getDo_question());
                douHolder.do_details.setText(doYou.getDo_answer());
//                do_pro.setVisibility(View.INVISIBLE);
            }
        };

        doAdapter.notifyDataSetChanged();
        doRecyclerView.setAdapter(doAdapter);
    }

    private void loadSubject() {

        adapter =
                new FirebaseRecyclerAdapter<TermModel, TermViewholder>(
                        TermModel.class,
                        R.layout.terms_subject_row,
                        TermViewholder.class,
                        termReference
                ) {
                    @Override
                    protected void populateViewHolder(TermViewholder termViewHolder, TermModel termModel, int i) {

                        termViewHolder.tText.setText(termModel.getSubjectName());
                        Picasso.get().load(termModel.getSubjectImage()).into(termViewHolder.tImage);
                        //menu_pro.setVisibility(View.INVISIBLE);
                        termViewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {
                                Intent next = new Intent(getApplicationContext(), TermViewActivity.class);
                                CommonModel.term_id = adapter.getRef(position).getKey();
                                startActivity(next);
                            }
                        });

                    }
                };

        adapter.notifyDataSetChanged();
        termRecyclerView.setAdapter(adapter);
    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });


        }
    }


    @Override
    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue reposting.")
                .setPositiveButton("App",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore(updateUrl);
                            }
                        }).create();
        dialog.show();
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

