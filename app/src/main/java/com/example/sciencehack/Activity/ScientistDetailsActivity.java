package com.example.sciencehack.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sciencehack.Model.ScientistViewModel;
import com.example.sciencehack.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.sciencehack.Model.CommonModel.scientist_id;

public class ScientistDetailsActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;

    CircleImageView scientist_img;
    TextView scientist_na,scientist_birth,scientist_death,scientist_country,scientist_field_name,scientist_awards_name,scientist_details,scientist_works,scientist_books;
    Toolbar toolbar;
    FirebaseDatabase sciDatabase;
    DatabaseReference sciRef;
    String sName,sBirth,sDeath,sCountry,sField,sAwards,sWorks,sDetails,sBook;

    private AdView mAdView,lAdView;
    private InterstitialAd sInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientist_details);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.madView);
        lAdView = findViewById(R.id.ladView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        lAdView.loadAd(adRequest);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        sInterstitialAd = new InterstitialAd(this);
        sInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        sInterstitialAd.loadAd(new AdRequest.Builder().build());

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar Title");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle("Scientist Profile");

        scientist_img = findViewById(R.id.scientist_img);
        scientist_na = findViewById(R.id.scientist_na);
        scientist_birth = findViewById(R.id.scientist_birth);
        scientist_death = findViewById(R.id.scientist_death);
        scientist_country = findViewById(R.id.scientist_country);
        scientist_field_name = findViewById(R.id.scientist_field_name);
        scientist_awards_name = findViewById(R.id.scientist_awards_name);
        scientist_details = findViewById(R.id.scientist_details);
        scientist_works = findViewById(R.id.scientist_works);
        scientist_books = findViewById(R.id.scientist_books);

        sciDatabase = FirebaseDatabase.getInstance();
        sciRef = sciDatabase.getReference("Scientist_Profiles");

        sciRef.orderByChild("scientist_id").equalTo(scientist_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot s : dataSnapshot.getChildren()){
                    ScientistViewModel sModel = s.getValue(ScientistViewModel.class);

                    sName = sModel.getScientist_name().toString().trim();
                    sBirth = sModel.getScientist_birth().toString().trim();
                    sDeath = sModel.getScientist_death().toString().trim();
                    sCountry = sModel.getScientist_country().toString().trim();
                    sField = sModel.getScientist_field().toString().trim();
                    sAwards = sModel.getScientist_award().toString().trim();
                    sWorks = sModel.getScientist_works().toString().trim();
                    sDetails = sModel.getScientist_details().toString().trim();
                    sBook = sModel.getScientist_book().toString().trim();

                    scientist_na.setText(sName);
                    scientist_birth.setText(sBirth);
                    scientist_death.setText(sDeath);
                    scientist_country.setText(sCountry);
                    scientist_field_name.setText(sField);
                    scientist_awards_name.setText(sAwards);
                    scientist_details.setText(sWorks);
                    scientist_works.setText(sDetails);
                    scientist_books.setText(sBook);

                    Picasso.get().load(sModel.getScientist_image()).into(scientist_img);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

        public void diplayAds(){
        if (sInterstitialAd.isLoaded()){
            sInterstitialAd.show();
        }
    }
}
