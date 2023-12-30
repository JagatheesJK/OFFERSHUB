package com.hub.offershub.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.listener.ImageChooseListener;

import java.util.List;

public class EditImageAdapter extends RecyclerView.Adapter<EditImageAdapter.ViewHolder> {
    private List<Uri> imageList;
    private ImageChooseListener imageChooseListener;
    private boolean isShop = false;

    public EditImageAdapter(List<Uri> imageList, ImageChooseListener imageChooseListener, boolean isShop) {
        this.imageList = imageList;
        this.imageChooseListener = imageChooseListener;
        this.isShop = isShop;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_image_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imageUri = imageList.get(position);
        if (imageUri != null) {
            holder.editImg.setImageURI(imageUri);
            holder.editDelete.setVisibility(View.VISIBLE);
        } else {
            holder.editDelete.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            imageChooseListener.onImageChoose();
        });

        holder.editDelete.setOnClickListener(v -> {
            imageList.remove(position);
            imageChooseListener.onImageRemove(imageList.size());
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        if (isShop)
            return 1;
        else
            return (imageList.size() <= 2) ? imageList.size() : 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView editImg, editDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editImg = itemView.findViewById(R.id.editImg);
            editDelete = itemView.findViewById(R.id.editDelete);
        }
    }
}
