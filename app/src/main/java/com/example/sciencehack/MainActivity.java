package com.example.sciencehack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.sciencehack.Menu_Fragment.NewsFragment;
import com.example.sciencehack.Menu_Fragment.QuotesFragment;
import com.example.sciencehack.Menu_Fragment.ScientistFragment;
import com.example.sciencehack.Menu_Fragment.TermFragment;
import com.example.sciencehack.Menu_Fragment.ValueFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.jar.JarOutputStream;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavView;
    private FrameLayout mFrameLayout;

    private TermFragment termFragment;
    private ValueFragment valueFragment;
    private QuotesFragment quotesFragment;
    private ScientistFragment scientistFragment;
    private NewsFragment newsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavView = findViewById(R.id.main_menu);
        mFrameLayout = findViewById(R.id.main_frame);

        termFragment = new TermFragment();
        valueFragment = new ValueFragment();
        quotesFragment = new QuotesFragment();
        scientistFragment = new ScientistFragment();
        newsFragment = new NewsFragment();

        setFragment(termFragment);

        mNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.nav_term:
                        setFragment(termFragment);
                        mNavView.setItemBackgroundResource(R.color.colorPrimary);
                        return true;

                    case R.id.nav_value:
                        setFragment(valueFragment);
                        mNavView.setItemBackgroundResource(R.color.colorPrimary);
                        return true;

                    case R.id.nav_quotes:
                        setFragment(quotesFragment);
                        mNavView.setItemBackgroundResource(R.color.colorPrimary);
                        return true;

                    case R.id.nav_scientist:
                        setFragment(scientistFragment);
                        mNavView.setItemBackgroundResource(R.color.colorPrimary);
                        return true;

                    case R.id.nav_journal:
                        setFragment(newsFragment);
                        mNavView.setItemBackgroundResource(R.color.colorPrimary);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }
}
