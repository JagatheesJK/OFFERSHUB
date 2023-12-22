package com.hub.offershub.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.listener.CommonListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.utils.CommonMethods;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    private List<BusinessModel.Data> list;
    private Context ctx;
    private CommonListener listener;
    private boolean isActive = false;
    private CommonMethods commonMethods;

    public BusinessAdapter(Context context, List<BusinessModel.Data> list, CommonListener listener, boolean isActive) {
        this.list = list;
        this.listener = listener;
        this.isActive = isActive;
        ctx = context;
        commonMethods = new CommonMethods();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusinessModel.Data model = list.get(position);
        if (model != null) {
            holder.statusTxt.setVisibility(isActive ? View.GONE : View.VISIBLE);
            holder.businessNameTxt.setText(""+model.shop_name);
            holder.addressTxt.setText(""+model.address1);
            holder.categoryTxt.setText(""+model.categoryname);
            holder.rateTxt.setText(""+model.total_rate+" ("+model.avg_rating+")");
            holder.statusTxt.setText(""+model.adminverifystatus);
            holder.statusTxt.setBackgroundResource(R.drawable.bg_rounded_8);
            commonMethods.imageLoaderView(ctx, holder.shopImg, model.image_url);
            if ("Verification Pending".equals(model.adminverifystatus)) {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.yellow, null)));
            } else if ("Verification Reject".equals(model.adminverifystatus)) {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.red, null)));
            } else {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.green, null)));
            }

            holder.itemView.setOnClickListener(v -> {
                listener.onItemSelected(model);
            });

            holder.editImg.setOnClickListener(v -> {
                listener.onItemEdited(model);
            });

            holder.deleteImg.setOnClickListener(v -> {
                listener.onItemRemoved(model);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeData(BusinessModel.Data model) {
        list.remove(model);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView businessNameTxt, addressTxt;
        AppCompatTextView categoryTxt, rateTxt, statusTxt;
        AppCompatImageView shopImg, editImg, deleteImg;
        public ViewHolder(View v) {
            super(v);
            businessNameTxt = v.findViewById(R.id.businessNameTxt);
            addressTxt = v.findViewById(R.id.addressTxt);
            categoryTxt = v.findViewById(R.id.categoryTxt);
            rateTxt = v.findViewById(R.id.rateTxt);
            statusTxt = v.findViewById(R.id.statusTxt);
            editImg = v.findViewById(R.id.editImg);
            deleteImg = v.findViewById(R.id.deleteImg);
            shopImg = v.findViewById(R.id.shopImg);
        }
    }
}