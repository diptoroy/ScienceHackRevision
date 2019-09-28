package com.example.sciencehack.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.Extra.DateFormatter;
import com.example.sciencehack.Interface.ItemClickListener;
import com.example.sciencehack.Model.JournalModel;
import com.example.sciencehack.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class JournalHolder extends RecyclerView.Adapter<JournalHolder.ViewHolder>  {

    ArrayList<JournalModel> journalModels;
    Context jCtx;
    private OnItemClickListner itemClickListner;

    public JournalHolder(ArrayList<JournalModel> journalModels, Context jCtx) {
        this.journalModels = journalModels;
        this.jCtx = jCtx;
    }

    @NonNull
    @Override
    public JournalHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View journalrow = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_row,parent,false);
        return new ViewHolder(journalrow);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalHolder.ViewHolder holder, int position) {

        JournalModel journal = journalModels.get(position);
        holder.j_title.setText(journal.getTitle());
        holder.j_detail.setText((journal.getDescription()));
        holder.j_date.setText(DateFormatter.dateFormatted(journal.getPublishedAt()));

        Picasso.get().load(journalModels.get(position).getUrlToImage()).into(holder.j_image);


    }

    @Override
    public int getItemCount() {
        return journalModels.size();
    }

    public void setOnClickListener(OnItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView j_image;
        public TextView j_title,j_detail,j_date;
        public ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            j_image = itemView.findViewById(R.id.j_img);
            j_title = itemView.findViewById(R.id.j_headline);
            j_detail = itemView.findViewById(R.id.j_detail);
            j_date = itemView.findViewById(R.id.j_date);


        }


        @Override
        public void onClick(View view) {
            itemClickListner.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListner {
        void onItemClick(View view, int position);
    }

}
