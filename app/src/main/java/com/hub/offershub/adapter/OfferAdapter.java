package com.hub.offershub.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.hub.offershub.R;
import com.hub.offershub.listener.OfferListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.CommonMethods;
import com.hub.offershub.utils.Utils;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<OfferModel.Data> list;
    private Context ctx;
    private CommonMethods commonMethods;
    private OfferListener listener;
    private boolean isActive = false;
    private int selectedPosition = RecyclerView.NO_POSITION;
    String priority;
    private BusinessModel.Data businessModel;

    public OfferAdapter(Context context, List<OfferModel.Data> list, OfferListener listener, boolean isActive, BusinessModel.Data businessModel) {
        this.list = list;
        ctx = context;
        this.listener = listener;
        this.isActive = isActive;
        this.businessModel = businessModel;
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
        OfferModel.Data model = list.get(position);
        if (model != null) {
            /*if (selectedPosition != position) {
                if (selectedPosition == RecyclerView.NO_POSITION)
                    selectedPosition = (model.is_show_mainpage == 1) ? position : RecyclerView.NO_POSITION;
            }*/
            Log.e("Check_JK", "Type selectedPosition : "+selectedPosition+" position : "+position);
            holder.statusTxt.setVisibility(isActive ? View.GONE : View.VISIBLE);
            holder.switchLinear.setVisibility(isActive ? View.VISIBLE : View.GONE);
            holder.offerNameTxt.setText(""+model.offer_name);
            holder.offerDescTxt.setText(""+model.offer_desc);
            holder.offerType.setText("Type : "+model.offer_type_name);
//            holder.offerSwitch.setChecked(selectedPosition == position);
            holder.offerSwitch.setChecked((model.is_show_mainpage == 1));
            if (1 == model.offer_type) {
                Log.e("Check_JK", "Type : "+model.offer_type+" IF ");
                holder.amountLinear.setVisibility(View.VISIBLE);
                holder.discountLinear.setVisibility(View.GONE);
                if (model.amount != null && !"".equals(model.amount))
                    holder.offerPrice.setText("₹ "+ Utils.addComma(Integer.parseInt(model.amount)));
                holder.offerPrice.setTextColor(ctx.getColor(R.color.black));
            } else if (2 == model.offer_type) {
                Log.e("Check_JK", "Type : "+model.offer_type+" ELSE IF ");
                holder.amountLinear.setVisibility(View.GONE);
                holder.discountLinear.setVisibility(View.VISIBLE);
            } else {
                Log.e("Check_JK", "Type : "+model.offer_type+" ELSE ");
                holder.amountLinear.setVisibility(View.VISIBLE);
                holder.discountLinear.setVisibility(View.GONE);
                holder.offerPrice.setText("Flat "+model.flat_percentage+"%");
                holder.offerPrice.setTextColor(ctx.getColor(R.color.green));
            }
            holder.statusTxt.setText(""+model.adminverifystatus);
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
            }).error(R.drawable.def_logo).into(holder.offerImg);
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
            holder.originalAmountTxt.setPaintFlags(holder.originalAmountTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            if (model.original_amount != null && !"".equals(model.original_amount))
                holder.originalAmountTxt.setText("₹ "+Utils.addComma(Integer.parseInt(model.original_amount)));
            if (model.offer_amount != null && !"".equals(model.offer_amount))
                holder.discountPriceTxt.setText("₹ "+Utils.addComma(Integer.parseInt(model.offer_amount)));
            holder.discountOfferTxt.setText("OFF "+model.offer_percentage+"%");

            holder.offerSwitch.setOnClickListener(v -> {
                if (!"Expired".equals(businessModel.subscription_status)) {
                    holder.offerSwitch.setChecked(true);
                    if ((model.is_show_mainpage == 1))
                        priority = "0";
                    else
                        priority = "1";
                    /*if (selectedPosition != position) {
                        this.selectedPosition = position;
                        priority = "1";
                    } else {
                        selectedPosition = RecyclerView.NO_POSITION;
                        priority = "0";
                    }
                    notifyDataSetChanged();*/
                } else {
                    holder.offerSwitch.setChecked(false);
                }
                listener.onOfferSelect(model, priority);
            });

            holder.itemView.setOnClickListener(v -> {
//                listener.onOfferSelect();
            });

            holder.editLinear.setOnClickListener(v -> {
                listener.onOfferEdit(model);
            });

            holder.deleteLinear.setOnClickListener(v -> {
                listener.onOfferRemove(model, position);
            });

            holder.insightLinear.setOnClickListener(v -> {
                listener.onOfferInSight(model);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeData(OfferModel.Data model, int position) {
        list.remove(model);
        notifyDataSetChanged();
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, getItemCount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView offerNameTxt, offerDescTxt;
        AppCompatTextView offerType, offerPrice;
        AppCompatTextView statusTxt, categoryTxt;
        AppCompatTextView originalAmountTxt;
        AppCompatImageView offerImg;
        SwitchCompat offerSwitch;
        LinearLayoutCompat editLinear, deleteLinear, insightLinear;
        ConstraintLayout switchLinear;
        AppCompatTextView discountOfferTxt, discountPriceTxt;
        LinearLayoutCompat amountLinear, discountLinear;
        ShimmerFrameLayout shimmerFrameLayout;
        public ViewHolder(View v) {
            super(v);
            offerNameTxt = v.findViewById(R.id.offerNameTxt);
            offerDescTxt = v.findViewById(R.id.offerDescTxt);
            offerType = v.findViewById(R.id.offerType);
            offerPrice = v.findViewById(R.id.offerPrice);
            offerImg = v.findViewById(R.id.offerImg);
            offerSwitch = v.findViewById(R.id.offerSwitch);
            editLinear = v.findViewById(R.id.editLinear);
            deleteLinear = v.findViewById(R.id.deleteLinear);
            insightLinear = v.findViewById(R.id.insightLinear);
            statusTxt = v.findViewById(R.id.statusTxt);
            categoryTxt = v.findViewById(R.id.categoryTxt);
            switchLinear = v.findViewById(R.id.switchLinear);
            originalAmountTxt = v.findViewById(R.id.originalAmountTxt);
            discountOfferTxt = v.findViewById(R.id.discountOfferTxt);
            discountPriceTxt = v.findViewById(R.id.discountPriceTxt);
            amountLinear = v.findViewById(R.id.amountLinear);
            discountLinear = v.findViewById(R.id.discountLinear);
            shimmerFrameLayout = v.findViewById(R.id.shimmerFrameLayout);
        }
    }
}