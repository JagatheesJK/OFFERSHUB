package com.hub.offershub.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.listener.ImageChooseListener;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<Uri> imageList;
    private ImageChooseListener imageChooseListener;

    public ImageAdapter(List<Uri> imageList, ImageChooseListener imageChooseListener) {
        this.imageList = imageList;
        this.imageChooseListener = imageChooseListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imageUri = imageList.get(position);
        if (imageUri != null) {
            holder.imageView.setImageURI(imageUri);
        }

        holder.imageView.setOnClickListener(v -> {
            imageChooseListener.onImageChoose();
        });

        holder.deleteImg.setOnClickListener(v -> {
            imageList.remove(position);
            imageChooseListener.onImageRemove(imageList.size());
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return (imageList.size() <= 2) ? imageList.size() : 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imageView, deleteImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            deleteImg = itemView.findViewById(R.id.deleteImg);
        }
    }
}
