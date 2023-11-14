package com.hub.offershub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.model.BusinessModel;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    private List<BusinessModel> list;
    private Context ctx;

    public BusinessAdapter(Context context, List<BusinessModel> list) {
        this.list = list;
        ctx = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusinessModel model = list.get(position);
        if (model != null) {
            holder.businessNameTxt.setText(""+model.businessName);
            holder.addressTxt.setText(""+model.businessAddress);
            holder.categoryTxt.setText(""+model.businessCategory);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView businessNameTxt, addressTxt;
        AppCompatTextView categoryTxt;
        public ViewHolder(View v) {
            super(v);
            businessNameTxt = v.findViewById(R.id.businessNameTxt);
            addressTxt = v.findViewById(R.id.addressTxt);
            categoryTxt = v.findViewById(R.id.categoryTxt);
        }
    }
}