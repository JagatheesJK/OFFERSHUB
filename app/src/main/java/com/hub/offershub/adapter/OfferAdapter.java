package com.hub.offershub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hub.offershub.R;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.CommonMethods;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<OfferModel> list;
    private Context ctx;
    private CommonMethods commonMethods;

    public OfferAdapter(Context context, List<OfferModel> list) {
        this.list = list;
        ctx = context;
        commonMethods = new CommonMethods();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OfferModel model = list.get(position);
        if (model != null) {
            holder.offerNameTxt.setText(""+model.offerName);
            holder.offerDescTxt.setText(""+model.offerDesc);
            holder.offerType.setText("Type : "+model.offerType);
            holder.offerPrice.setText(""+model.offerPrice);
            holder.offerSwitch.setChecked(model.offerToggle);
            commonMethods.imageLoaderView(ctx, holder.offerImg, model.offerImage);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView offerNameTxt, offerDescTxt;
        AppCompatTextView offerType, offerPrice;
        AppCompatImageView offerImg;
        SwitchCompat offerSwitch;
        public ViewHolder(View v) {
            super(v);
            offerNameTxt = v.findViewById(R.id.offerNameTxt);
            offerDescTxt = v.findViewById(R.id.offerDescTxt);
            offerType = v.findViewById(R.id.offerType);
            offerPrice = v.findViewById(R.id.offerPrice);
            offerImg = v.findViewById(R.id.offerImg);
            offerSwitch = v.findViewById(R.id.offerSwitch);
        }
    }
}