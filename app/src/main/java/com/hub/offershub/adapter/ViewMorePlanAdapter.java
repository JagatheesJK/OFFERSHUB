package com.hub.offershub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;

import java.util.List;

public class ViewMorePlanAdapter extends RecyclerView.Adapter<ViewMorePlanAdapter.ViewHolder> {

    private List<String> list;
    private Context ctx;

    public ViewMorePlanAdapter(Context context, List<String> list) {
        this.list = list;
        ctx = context;
    }

    @NonNull
    @Override
    public ViewMorePlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewmore_plan_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMorePlanAdapter.ViewHolder holder, int position) {
        String des = list.get(position);
        if (des != null) {
            holder.planDesc.setText("* "+des);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView planDesc;
        public ViewHolder(View v) {
            super(v);
            planDesc = v.findViewById(R.id.planDesc);
        }
    }
}