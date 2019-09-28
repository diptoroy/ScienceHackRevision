package com.example.sciencehack.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sciencehack.Activity.QuotesActivity;
import com.example.sciencehack.Model.QuotesModel;
import com.example.sciencehack.R;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class QuotesHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context qCtx;
    private List<Object> quotesModels = new ArrayList<>();

    private static final int rc_item = 0;
    private static final int rc_ads = 1;

    public QuotesHolder(Context qCtx, List<Object> quotesModels) {
        this.qCtx = qCtx;
        this.quotesModels = quotesModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case rc_item:
                return new MyQuoteHolder(LayoutInflater.from(qCtx).inflate(R.layout.quotes_view_row, parent, false));
            case rc_ads:

            default:
                return new AdViewHolder(LayoutInflater.from(qCtx).inflate(R.layout.bannar_ads, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType){
            case rc_item:
                MyQuoteHolder myQuoteHolder = (MyQuoteHolder) holder;
                QuotesModel qModel = (QuotesModel) quotesModels.get(position);
                myQuoteHolder.quotes.setText(qModel.getQuotes());
                myQuoteHolder.quotesAuthor.setText(qModel.getQuotesAuthor());
                break;
            case rc_ads:

            default:
                AdViewHolder adViewHolder = (AdViewHolder) holder;
                AdView adView = (AdView) quotesModels.get(position);
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
        return quotesModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position% QuotesActivity.ITEM_PER_ADS == 3){
            return rc_ads;
        }
        return rc_item;
    }

    class MyQuoteHolder extends RecyclerView.ViewHolder {

        TextView quotes;
        TextView quotesAuthor;

        public MyQuoteHolder(@NonNull View itemView) {
            super(itemView);

            quotes = itemView.findViewById(R.id.quotes);
            quotesAuthor = itemView.findViewById(R.id.quoAuthor);
        }
    }

    class AdViewHolder extends RecyclerView.ViewHolder{

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
