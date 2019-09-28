package com.example.sciencehack.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sciencehack.Extra.InternetCheck;
import com.example.sciencehack.R;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    Animation rotateAnimation,fade_in;
    ProgressBar splash_progressBar;
    TextView motto_text;

    private static int SPLASH_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        InternetCheck.isConnected(this);

        logo = findViewById(R.id.logo);
//        rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
//        logo.setAnimation(rotateAnimation);
        splash_progressBar = findViewById(R.id.splash_progressBar);
        splash_progressBar.setVisibility(View.VISIBLE);

        motto_text = findViewById(R.id.motto_text);

        fade_in = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        logo.setAnimation(fade_in);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (InternetCheck.isConnected(getApplicationContext())){
                    Intent next = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(next);
                    finish();
                }else{
                    showDialog();
                    splash_progressBar.setVisibility(View.INVISIBLE);

                }
            }
        },SPLASH_OUT);
    }

    private void showDialog() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Not Connected")
                .setIcon(R.drawable.warning)
                .setView(R.layout.setting_dialog)
                .setCancelable(false)
                .setMessage("You are not connected to the Internet");
        final AlertDialog mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {
                final Button button =  mDialog.findViewById(R.id.closeBtn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Do your validations task here
                        if (InternetCheck.isConnected(getApplicationContext())){
                            mDialog.dismiss();
                            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
        mDialog.getWindow().getAttributes().windowAnimations = R.style.scale;
        mDialog.show();
    }
}
