package com.hub.offershub.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hub.offershub.R;
import com.hub.offershub.model.AdminOfferImageModel;
import com.hub.offershub.model.AdminOfferModel;
import com.hub.offershub.utils.Utils;
import com.hub.offershub.utils.blur.BlurTransformation;

import java.util.List;

public class AdminOfferAdapter extends RecyclerView.Adapter<AdminOfferAdapter.ViewHolder> {

    private List<AdminOfferModel.Data> list;
    private List<AdminOfferImageModel.Data> shopImageList;
    private Context ctx;
    private boolean isShopList = false;
    private boolean isPending = false;
    private AdminOfferPendingListener listener;

    public AdminOfferAdapter(Context context, List<AdminOfferModel.Data> list, List<AdminOfferImageModel.Data> shopImageList, boolean isShopList, AdminOfferPendingListener listener, boolean isPending) {
        this.ctx = context;
        this.list = list;
        this.shopImageList = shopImageList;
        this.isShopList = isShopList;
        this.listener = listener;
        this.isPending = isPending;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_shop_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isShopList) {
            AdminOfferModel.Data model = list.get(position);
            holder.shopListCard.setVisibility(View.VISIBLE);
            holder.shopImageCard.setVisibility(View.GONE);
            holder.shopListBottomLinear.setVisibility((isPending) ? View.VISIBLE : View.GONE);
            if (model != null) {
                holder.shopNameTxt.setText(""+model.offer_name);
                holder.shopDescTxt.setText(""+model.offer_desc);

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

                holder.acceptLinear.setOnClickListener(v -> {
                    holder.acceptLinear.setEnabled(false);
                    listener.onApproval("approve", model, null);
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        holder.acceptLinear.setEnabled(true);
                    }, 800);
                });

                holder.rejectLinear.setOnClickListener(v -> {
                    holder.rejectLinear.setEnabled(false);
                    listener.onReject("reject", model, null);
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        holder.rejectLinear.setEnabled(true);
                    }, 800);
                });
            }
        }
        else {
            AdminOfferImageModel.Data model = shopImageList.get(position);
            holder.shopListCard.setVisibility(View.GONE);
            holder.shopImageCard.setVisibility(View.VISIBLE);
            holder.shopImageBottomLinear.setVisibility((isPending) ? View.VISIBLE : View.GONE);
            if (model != null) {
                Glide.with(ctx).load(model.image_stored_path)
                        .apply(RequestOptions.bitmapTransform(new BlurTransformation(80, 3)))
                        .into(holder.shopBackImag);

                holder.progressBar.setVisibility(View.VISIBLE);
                Glide.with(ctx).load(model.image_stored_path).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.shopImg);

                holder.acceptImgLinear.setOnClickListener(v -> {
                    holder.acceptImgLinear.setEnabled(false);
                    listener.onApproval("approve", null, model);
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        holder.acceptImgLinear.setEnabled(true);
                    }, 800);
                });

                holder.rejectImgLinear.setOnClickListener(v -> {
                    holder.rejectImgLinear.setEnabled(false);
                    listener.onReject("reject", null, model);
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        holder.rejectImgLinear.setEnabled(true);
                    }, 800);
                });
            }
        }
    }

    public void removeData(AdminOfferModel.Data model) {
        try {
            list.remove(model);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeData(AdminOfferImageModel.Data model) {
        try {
            shopImageList.remove(model);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (isShopList)
            return list.size();
        else
            return shopImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView shopNameTxt, shopDescTxt;
        LinearLayoutCompat acceptLinear, rejectLinear;
        AppCompatImageView shopBackImag, shopImg;
        LinearLayoutCompat acceptImgLinear, rejectImgLinear;
        LinearLayoutCompat shopListBottomLinear, shopImageBottomLinear;
        CardView shopListCard, shopImageCard;
        ProgressBar progressBar;
        AppCompatTextView offerPrice;
        LinearLayoutCompat amountLinear, discountLinear;
        public ViewHolder(View v) {
            super(v);
            shopNameTxt = v.findViewById(R.id.shopNameTxt);
            shopDescTxt = v.findViewById(R.id.shopDescTxt);
            acceptLinear = v.findViewById(R.id.acceptLinear);
            rejectLinear = v.findViewById(R.id.rejectLinear);
            shopBackImag = v.findViewById(R.id.shopBackImag);
            shopImg = v.findViewById(R.id.shopImg);
            acceptImgLinear = v.findViewById(R.id.acceptImgLinear);
            rejectImgLinear = v.findViewById(R.id.rejectImgLinear);
            shopListBottomLinear = v.findViewById(R.id.shopListBottomLinear);
            shopImageBottomLinear = v.findViewById(R.id.shopImageBottomLinear);
            shopListCard = v.findViewById(R.id.shopListCard);
            shopImageCard = v.findViewById(R.id.shopImageCard);
            progressBar = v.findViewById(R.id.progressBar);
            offerPrice = v.findViewById(R.id.offerPrice);
            amountLinear = v.findViewById(R.id.amountLinear);
            discountLinear = v.findViewById(R.id.discountLinear);
        }
    }

    public interface AdminOfferPendingListener {
        void onApproval(String type, AdminOfferModel.Data model, AdminOfferImageModel.Data imageModel);
        void onReject(String type, AdminOfferModel.Data model, AdminOfferImageModel.Data imageModel);
    }

}