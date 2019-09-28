package com.example.sciencehack.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.Interface.ItemClickListener;
import com.example.sciencehack.Model.ScientistModel;
import com.example.sciencehack.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScientistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView scientistName;
    public CircleImageView scientistImage;
    public ItemClickListener itemClickListener;

    public ScientistHolder(@NonNull View itemView) {
        super(itemView);

        scientistName = itemView.findViewById(R.id.scientist_name);
        scientistImage = itemView.findViewById(R.id.scientist_image);

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
