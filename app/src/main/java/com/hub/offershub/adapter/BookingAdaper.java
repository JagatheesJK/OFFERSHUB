package com.hub.offershub.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
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
import com.hub.offershub.listener.BookingListener;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.utils.Utils;

import java.util.List;

public class BookingAdaper extends RecyclerView.Adapter<BookingAdaper.BookViewHolder> {

    private List<BookModel.Data> list;
    private Context ctx;
    private BookingListener listener;

    public BookingAdaper(Context context, List<BookModel.Data> list, BookingListener listener) {
        this.list = list;
        ctx = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list_layout, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookModel.Data model = list.get(position);
        if (model != null) {
            holder.offerNameTxt.setText(""+model.offer_name);
            holder.userNameTxt.setText(""+model.name);
            holder.userMobileTxt.setText(""+model.fullmobile);
            holder.statusTxt.setText(""+model.userstatus);
            if (model.user_ordered_date != null && model.user_ordered_date.length() > 0)
                holder.bookingDateTxt.setText(""+ Utils.convertDateFormat(model.user_ordered_date));
            if (0 == model.is_shop_read) {
                holder.unreadDotImg.setVisibility(View.VISIBLE);
                holder.offerNameTxt.setTextColor(ctx.getColor(R.color.black));
                holder.userNameTxt.setTextColor(ctx.getColor(R.color.black));
                holder.userMobileTxt.setTextColor(ctx.getColor(R.color.black));
                holder.bookingDateTxt.setTextColor(ctx.getColor(R.color.black));

                holder.offerNameTxt.setTypeface(null, Typeface.BOLD);
                holder.userNameTxt.setTypeface(null, Typeface.BOLD);
                holder.userMobileTxt.setTypeface(null, Typeface.BOLD);
                holder.bookingDateTxt.setTypeface(null, Typeface.BOLD);
            } else {
                holder.unreadDotImg.setVisibility(View.GONE);
                holder.userNameTxt.setTextColor(ctx.getColor(R.color.default_txt));
                holder.userMobileTxt.setTextColor(ctx.getColor(R.color.default_txt));
                holder.bookingDateTxt.setTextColor(ctx.getColor(R.color.default_txt));

                holder.offerNameTxt.setTypeface(null, Typeface.BOLD);
                holder.userNameTxt.setTypeface(null, Typeface.NORMAL);
                holder.userMobileTxt.setTypeface(null, Typeface.NORMAL);
                holder.bookingDateTxt.setTypeface(null, Typeface.NORMAL);
            }
            if ("Pending".equals(model.userstatus)) {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.yellow, null)));
                holder.buttonLinear.setVisibility(View.VISIBLE);
            } else if ("Rejected".equals(model.userstatus)) {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.red, null)));
                holder.buttonLinear.setVisibility(View.GONE);
            } else {
                holder.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        ctx.getResources(), R.color.green, null)));
                holder.buttonLinear.setVisibility(View.GONE);
            }
            holder.shimmerFrameLayout.startShimmer();
            Glide.with(ctx).load("").listener(new RequestListener<Drawable>() {
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

            holder.itemView.setOnClickListener(v -> {
                listener.onBookingSelect(model);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView offerNameTxt, userNameTxt;
        AppCompatTextView userMobileTxt, bookingDateTxt;
        AppCompatImageView offerImg, unreadDotImg;
        AppCompatTextView statusTxt;
        LinearLayoutCompat buttonLinear;
        AppCompatButton rejectBtn, confirmBtn;
        ShimmerFrameLayout shimmerFrameLayout;
        public BookViewHolder(View v) {
            super(v);
            offerNameTxt = v.findViewById(R.id.offerNameTxt);
            userNameTxt = v.findViewById(R.id.userNameTxt);
            userMobileTxt = v.findViewById(R.id.userMobileTxt);
            bookingDateTxt = v.findViewById(R.id.bookingDateTxt);
            offerImg = v.findViewById(R.id.offerImg);
            unreadDotImg = v.findViewById(R.id.unreadDotImg);
            statusTxt = v.findViewById(R.id.statusTxt);
            buttonLinear = v.findViewById(R.id.buttonLinear);
            rejectBtn = v.findViewById(R.id.rejectBtn);
            confirmBtn = v.findViewById(R.id.confirmBtn);
            shimmerFrameLayout = v.findViewById(R.id.shimmerFrameLayout);
        }
    }
}
