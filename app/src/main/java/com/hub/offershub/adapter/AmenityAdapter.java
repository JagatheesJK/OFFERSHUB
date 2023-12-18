package com.hub.offershub.adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.model.Amenity;

import java.util.ArrayList;
import java.util.List;

public class AmenityAdapter extends RecyclerView.Adapter<AmenityAdapter.ViewHolder> {
    private List<Amenity.AmenityItem> amenities;
    private SparseBooleanArray selectedItems;

    public AmenityAdapter(List<Amenity.AmenityItem> amenities) {
        this.amenities = amenities;
        selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amenity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Amenity.AmenityItem amenity = amenities.get(position);
        holder.bind(amenity, selectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return amenities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkboxAmenity);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    toggleSelection(position);
                }
            });
        }

        public void bind(Amenity.AmenityItem amenity, boolean isSelected) {
            checkBox.setText(amenity.getAmenities());
            checkBox.setChecked(isSelected);
        }
    }

    private void toggleSelection(int position) {
        boolean isSelected = selectedItems.get(position, false);
        selectedItems.put(position, !isSelected);
        notifyItemChanged(position);
    }

    public List<Integer> getSelectedAmenityIds() {
        List<Integer> selectedIds = new java.util.ArrayList<>();
        for (int i = 0; i < amenities.size(); i++) {
            if (selectedItems.get(i, false)) {
                selectedIds.add(amenities.get(i).getId());
            }
        }
        return selectedIds;
    }
}


