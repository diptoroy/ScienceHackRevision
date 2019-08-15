package com.example.sciencehack.Menu_Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sciencehack.Model.TermModel;
import com.example.sciencehack.R;
import com.example.sciencehack.ViewHolder.TermViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermFragment extends Fragment {

    View termView;
    RecyclerView termRecyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    FirebaseRecyclerAdapter<TermModel, TermViewHolder> adapter;
    FirebaseDatabase termDatabase;
    DatabaseReference termReference;

    public TermFragment() {
        // Required empty public constructor
    }
//
//    public static TermFragment newInstance(){
//        TermFragment quizFragment = new TermFragment();
//        return quizFragment;
//    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        termDatabase = FirebaseDatabase.getInstance();
        termReference = termDatabase.getReference("Term_Subject");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        termView =  inflater.inflate(R.layout.fragment_term, container, false);
        termRecyclerView = termView.findViewById(R.id.term_recyclerView_id);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        termRecyclerView.setLayoutManager(staggeredGridLayoutManager);
//        termRecyclerView.setHasFixedSize(true);
//        termRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadSubject();
        return termView;
    }

    private void loadSubject() {
        adapter =
                new FirebaseRecyclerAdapter<TermModel, TermViewHolder>(
                        TermModel.class,
                        R.layout.terms_subject_row,
                        TermViewHolder.class,
                        termReference
                ) {
                    @Override
                    protected void populateViewHolder(TermViewHolder termViewHolder, TermModel termModel, int i) {

                        termViewHolder.setDetails(getActivity(),termModel.getSubjectImage(),termModel.getSubjectName());
                    }
                };
        adapter.notifyDataSetChanged();
        termRecyclerView.setAdapter(adapter);
    }

//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }
}
