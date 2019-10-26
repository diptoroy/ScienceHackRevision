package com.example.sciencehack.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sciencehack.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ContactActivity extends AppCompatActivity {

    Toolbar mail_toolbar;
    private AdView cAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mail_toolbar = findViewById(R.id.toolbar_mail);
        mail_toolbar.setTitle("Contact Us");
        setSupportActionBar(mail_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText subject = findViewById(R.id.Subject);
        final EditText msg = findViewById(R.id.c_Message);
        final Button sendbtn = findViewById(R.id.send);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = subject.getText().toString().trim();
                String message = msg.getText().toString().trim();
                sendTomail(sub,message);

            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        cAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        cAdView.loadAd(adRequest);

    }

    private void sendTomail(String sub, String message) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"diptoroy54@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,new String[]{sub});
        emailIntent.putExtra(Intent.EXTRA_TEXT,new String[]{message});
        emailIntent.setType("text/plain");
        try {
            startActivity(Intent.createChooser(emailIntent,"choose an email client"));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
