package com.example.sciencehack.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.Interface.ItemClickListener;
import com.example.sciencehack.R;

public class FeaturesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView f_text;
    public ImageView f_img;

    public ItemClickListener itemClickListener;
    public FeaturesHolder(@NonNull View itemView) {
        super(itemView);
        f_text = itemView.findViewById(R.id.fsubject_name);
        f_img = itemView.findViewById(R.id.fsubject_image);

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
