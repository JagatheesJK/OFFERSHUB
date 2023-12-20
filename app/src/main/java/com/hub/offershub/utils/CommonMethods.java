package com.hub.offershub.utils;

import static com.bumptech.glide.load.engine.DiskCacheStrategy.ALL;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.hub.offershub.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommonMethods {
    public void imageLoaderCircleImage(Context context, CircleImageView imageView, String imageUrl) {
        Glide.with(context.getApplicationContext()).load(imageUrl).thumbnail(0.4f).centerInside().fitCenter().diskCacheStrategy(ALL).into(imageView);
    }

    public void imageCircle(Context context, CircleImageView imageView, String imageUrl){
        Glide.with(context.getApplicationContext()).load(imageUrl).thumbnail(0.4f).error(R.drawable.def_logo).centerCrop().diskCacheStrategy(ALL).into(imageView);
    }

    public void imageLoaderView(Context context, AppCompatImageView imageView, String imageUrl) {
        Glide.with(context.getApplicationContext()).load(imageUrl).thumbnail(0.4f).error(R.drawable.def_logo).centerInside().fitCenter().diskCacheStrategy(ALL).into(imageView);
    }

    public void imageLoaderView(Context context, AppCompatImageView imageView, int imageUrl) {
        Glide.with(context.getApplicationContext()).load(imageUrl).thumbnail(0.4f).centerInside().fitCenter().diskCacheStrategy(ALL).into(imageView);
    }
}
