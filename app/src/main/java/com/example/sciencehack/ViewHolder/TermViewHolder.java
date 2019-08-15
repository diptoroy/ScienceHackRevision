package com.example.sciencehack.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.R;
import com.squareup.picasso.Picasso;

public class TermViewHolder extends RecyclerView.ViewHolder {

    View tView;

    public TermViewHolder(@NonNull View itemView) {
        super(itemView);
        tView = itemView;
    }

    public void setDetails(Context ctx,String image,String name){

        ImageView tImage = tView.findViewById(R.id.subject_image);
        TextView tText = tView.findViewById(R.id.subject_name);

        Picasso.get().load(image).into(tImage);
        tText.setText(name);
    }
}
