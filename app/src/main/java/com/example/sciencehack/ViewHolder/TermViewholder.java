package com.example.sciencehack.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.Interface.ItemClickListener;
import com.example.sciencehack.R;

public class TermViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tText;
    public ImageView tImage;

    public ItemClickListener itemClickListener;

    public TermViewholder(@NonNull View itemView) {
        super(itemView);

        tText = itemView.findViewById(R.id.subject_name);
        tImage = itemView.findViewById(R.id.subject_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }
}
