package com.example.sciencehack.Menu_Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sciencehack.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuotesFragment extends Fragment {


    public QuotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quotes, container, false);
    }

}
