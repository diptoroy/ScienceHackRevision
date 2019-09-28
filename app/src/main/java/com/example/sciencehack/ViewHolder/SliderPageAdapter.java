package com.example.sciencehack.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.sciencehack.R;
import com.example.sciencehack.Model.SliderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderPageAdapter extends PagerAdapter {

    private Context ctx;
    List<SliderModel> SliderModels;

    public SliderPageAdapter(Context ctx, List<SliderModel> SliderModels) {
        this.ctx = ctx;
        this.SliderModels = SliderModels;
    }

    @Override
    public int getCount() {
        return SliderModels.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slider_row,null);

        ImageView slideImg = slideLayout.findViewById(R.id.sliderimg);
        Picasso.get().load(SliderModels.get(position).getImage()).into(slideImg);
        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
