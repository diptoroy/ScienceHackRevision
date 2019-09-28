package com.example.sciencehack.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.Activity.TermViewActivity;
import com.example.sciencehack.Model.TermModel;
import com.example.sciencehack.Model.TermViewModel;
import com.example.sciencehack.R;
import com.google.android.gms.ads.AdView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TermHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
    private List<Object> termList = new ArrayList<>();
    private static final int rc_item = 0;
    private static final int rc_ads = 1;

    public TermHolder(Context ctx, List<Object> termList) {
        this.ctx = ctx;
        this.termList = termList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case rc_item:
                return new MyViewHolder(LayoutInflater.from(ctx).inflate(R.layout.term_view_row, parent, false));
            case rc_ads:

            default:
                return new AdViewHolder(LayoutInflater.from(ctx).inflate(R.layout.bannar_ads, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case rc_item:
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                TermViewModel tvModel = (TermViewModel) termList.get(position);
                myViewHolder.tName.setText(tvModel.getTitle());
                myViewHolder.tDetails.setText(tvModel.getFormula());
                break;
            case rc_ads:

            default:
                AdViewHolder adViewHolder = (AdViewHolder) holder;
                AdView adView = (AdView) termList.get(position);
                ViewGroup adCardView = (ViewGroup) adViewHolder.itemView;
                if (adCardView.getChildCount() > 0){
                    adCardView.removeAllViews();
                }
                if (adCardView.getParent()!=null){
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adCardView.addView(adView);
        }
    }

    @Override
    public int getItemCount() {
        return termList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position% TermViewActivity.ITEM_PER_ADS == 3){
            return rc_ads;
        }
        return rc_item;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tName;
        public TextView tDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tName = itemView.findViewById(R.id.term_name);
            tDetails = itemView.findViewById(R.id.term_Details);
        }
    }

    class AdViewHolder extends RecyclerView.ViewHolder{

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void update(List<Object> newList){
        termList = new ArrayList<>();
        termList.addAll(newList);
        notifyDataSetChanged();
    }

}
