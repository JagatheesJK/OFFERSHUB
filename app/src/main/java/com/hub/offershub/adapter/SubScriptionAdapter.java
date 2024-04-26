package com.hub.offershub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.listener.onPlanSelectListener;
import com.hub.offershub.model.SubscriptionPackageResponse;
import com.hub.offershub.utils.Utils;

import java.util.List;

public class SubScriptionAdapter extends RecyclerView.Adapter<SubScriptionAdapter.ViewHolder> {

    private List<SubscriptionPackageResponse.SubscriptionPackage> list;
    private int selectedPosition = 0;
    onPlanSelectListener listener;
    private boolean isFirst = true;
    SubscriptionPackageResponse.SubscriptionPackage selectedModel;

    public SubScriptionAdapter(onPlanSelectListener listener, List<SubscriptionPackageResponse.SubscriptionPackage> list) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubscriptionPackageResponse.SubscriptionPackage model = list.get(position);
        if (model != null) {
            if (position == 0)
                selectedModel = model;
            if (model.isActive == 1) {
                holder.plantitle.setText(model.packageName);
                holder.planAmt.setText("₹ "+ Utils.addComma(model.price));
                holder.planDays.setText(model.days+" days");
                holder.planRadio.setChecked(selectedPosition == position);
            }
        }

        holder.itemView.setOnClickListener(v -> {
            // Update selectedPosition and notify data changed
            if (selectedPosition != position) {
                selectedPosition = position;
                selectedModel = model;
                notifyDataSetChanged(); // Notify adapter to update checked state
            }
        });

        holder.viewMoreLinear.setOnClickListener(v -> {
            listener.onPlanViewMore(model);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public SubscriptionPackageResponse.SubscriptionPackage getSelectedModel() {
        return selectedModel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView plantitle;
        AppCompatTextView planAmt,planDays;
        RadioButton planRadio;
        LinearLayoutCompat viewMoreLinear;
        public ViewHolder(View v) {
            super(v);
            plantitle = v.findViewById(R.id.plantitle);
            planAmt = v.findViewById(R.id.planAmt);
            planDays = v.findViewById(R.id.planDays);
            planRadio = v.findViewById(R.id.planRadio);
            viewMoreLinear = v.findViewById(R.id.viewMoreLinear);
        }
    }
}