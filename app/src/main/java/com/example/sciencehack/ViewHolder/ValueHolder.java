package com.example.sciencehack.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.Activity.ValueActivity;
import com.example.sciencehack.Model.ValueModel;
import com.example.sciencehack.R;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class ValueHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context vCtx;
    private List<Object> valueModelList = new ArrayList<>();

    private static final int rc_item = 0;
    private static final int rc_ads = 1;

    public ValueHolder(Context vCtx, List<Object> valueModelList) {
        this.vCtx = vCtx;
        this.valueModelList = valueModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case rc_item:
                return new MyValueHolder(LayoutInflater.from(vCtx).inflate(R.layout.value_view_row,parent,false));
            case rc_ads:

            default:
                return new AdViewHolder(LayoutInflater.from(vCtx).inflate(R.layout.bannar_ads,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType){
            case rc_item:
                MyValueHolder myValueHolder = (MyValueHolder) holder;
                ValueModel vModel = (ValueModel) valueModelList.get(position);
                myValueHolder.valueName.setText(vModel.getValueName());
                myValueHolder.valueDetails.setText(vModel.getValueDetails());
                break;
            case rc_ads:
            default:
                AdViewHolder adViewHolder = (AdViewHolder) holder;
                AdView adView = (AdView) valueModelList.get(position);
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
        return valueModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position% ValueActivity.ITEM_PER_ADS == 3){
            return rc_ads;
        }
        return rc_item;
    }

    class MyValueHolder extends RecyclerView.ViewHolder {

        TextView valueName;
        TextView valueDetails;
        public MyValueHolder(@NonNull View itemView) {
            super(itemView);

            valueName = itemView.findViewById(R.id.value_name);
            valueDetails = itemView.findViewById(R.id.value_details);
        }
    }

    class AdViewHolder extends RecyclerView.ViewHolder{

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
