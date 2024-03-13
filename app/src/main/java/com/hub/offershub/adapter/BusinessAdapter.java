package com.hub.offershub.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;
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

    private String addr1, addr2, city, state;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusinessModel.Data model = list.get(position);
        if (model != null) {
            holder.statusTxt.setVisibility(isActive ? View.GONE : View.VISIBLE);
            holder.businessNameTxt.setText(""+model.shop_name);
            addr1 = (((model.address1 != null) && (model.address1.length() != 0)) ? model.address1+", " : "");
            addr2 = (((model.address2 != null) && (model.address2.length() != 0)) ? model.address2+", " : "");
            city = (((model.city != null) &&  (model.city.length() != 0)) ? model.city+", " : "");
            state = (((model.state != null) && (model.state.length() != 0)) ? model.state : "");
            holder.addressTxt.setText(""+addr1+addr2+city+state);
            holder.categoryTxt.setText(""+model.categoryname);
            holder.rateTxt.setText(""+model.avg_rating+" ("+model.total_rate+")");
            holder.statusTxt.setText(""+model.adminverifystatus);
            holder.subcriptionEndDateTxt.setText(""+model.subscription_end_date);
            holder.paymentStatusTxt.setText(""+model.subscription_status);
            holder.statusTxt.setBackgroundResource(R.drawable.bg_rounded_8);
            holder.shimmerFrameLayout.startShimmer();
            Glide.with(ctx).load(model.image_url).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setVisibility(View.GONE);
                    return false;
                }
            }).error(R.drawable.def_logo).into(holder.shopImg);
            if ("Pending".equals(model.adminverifystatus)) {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.yellow, null)));
            } else if ("Rejected".equals(model.adminverifystatus)) {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.red, null)));
            } else {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.green, null)));
            }

            holder.itemView.setOnClickListener(v -> {
                listener.onItemSelected(model);
            });

            holder.editLinear.setOnClickListener(v -> {
                listener.onItemEdited(model);
            });

            holder.deleteLinear.setOnClickListener(v -> {
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
        AppCompatTextView businessNameTxt, addressTxt, subcriptionEndDateTxt;
        AppCompatTextView categoryTxt, rateTxt, statusTxt, paymentStatusTxt;
        AppCompatImageView shopImg;
        ShimmerFrameLayout shimmerFrameLayout;
        LinearLayoutCompat editLinear, deleteLinear;
        public ViewHolder(View v) {
            super(v);
            businessNameTxt = v.findViewById(R.id.businessNameTxt);
            addressTxt = v.findViewById(R.id.addressTxt);
            categoryTxt = v.findViewById(R.id.categoryTxt);
            rateTxt = v.findViewById(R.id.rateTxt);
            statusTxt = v.findViewById(R.id.statusTxt);
            paymentStatusTxt = v.findViewById(R.id.paymentStatusTxt);
            subcriptionEndDateTxt = v.findViewById(R.id.subcriptionEndDateTxt);
            shopImg = v.findViewById(R.id.shopImg);
            shimmerFrameLayout = v.findViewById(R.id.shimmerFrameLayout);
            editLinear = v.findViewById(R.id.editLinear);
            deleteLinear = v.findViewById(R.id.deleteLinear);
        }
    }
}