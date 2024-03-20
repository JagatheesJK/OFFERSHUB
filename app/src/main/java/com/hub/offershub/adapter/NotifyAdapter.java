package com.hub.offershub.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.listener.NotifyListener;
import com.hub.offershub.model.BookModel;

import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {

    private List<BookModel.Data> list;
    private Context ctx;
    private NotifyListener listener;

    public NotifyAdapter(Context context, List<BookModel.Data> list, NotifyListener listener) {
        this.list = list;
        ctx = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyAdapter.ViewHolder holder, int position) {
        BookModel.Data model = list.get(position);
        if (model != null) {
            holder.shopNameTxt.setText(""+model.shop_name);
            holder.offerNameTxt.setText(""+model.offer_name);
            holder.userNameTxt.setText(""+model.name);
            holder.userMobileTxt.setText(""+model.mobile);
            holder.bookingDateTxt.setText(""+model.user_ordered_date);

            holder.mainCard.setOnClickListener(v -> {
                listener.onNotifySelect(model);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView mainCard;
        AppCompatTextView shopNameTxt, offerNameTxt, userNameTxt;
        AppCompatTextView userMobileTxt, bookingDateTxt, badgeTxt;
        public ViewHolder(View v) {
            super(v);
            mainCard = v.findViewById(R.id.mainCard);
            shopNameTxt = v.findViewById(R.id.shopNameTxt);
            offerNameTxt = v.findViewById(R.id.offerNameTxt);
            userNameTxt = v.findViewById(R.id.userNameTxt);
            userMobileTxt = v.findViewById(R.id.userMobileTxt);
            bookingDateTxt = v.findViewById(R.id.bookingDateTxt);
            badgeTxt = v.findViewById(R.id.badgeTxt);
        }
    }

}