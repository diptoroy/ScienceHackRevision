package com.example.sciencehack.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.R;

public class DouHolder extends RecyclerView.ViewHolder {

    public TextView do_title;
    public TextView do_details;

    public DouHolder(@NonNull View itemView) {
        super(itemView);

        do_title = itemView.findViewById(R.id.do_question);
        do_details = itemView.findViewById(R.id.do_answer);
    }
}
