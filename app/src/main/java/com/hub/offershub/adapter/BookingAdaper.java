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

import java.util.ArrayList;
import java.util.List;

public class BookingAdaper extends RecyclerView.Adapter<BookingAdaper.BookViewHolder> {

    private List<BookModel> list;
    private Context ctx;

    public BookingAdaper(Context context, List<BookModel> list) {
        this.list = list;
        ctx = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_list_layout, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView offerNameTxt, userNameTxt;
        AppCompatTextView userMobileTxt, bookingDateTxt;
        public BookViewHolder(View v) {
            super(v);
            offerNameTxt = v.findViewById(R.id.offerNameTxt);
            userNameTxt = v.findViewById(R.id.userNameTxt);
            userMobileTxt = v.findViewById(R.id.userMobileTxt);
            bookingDateTxt = v.findViewById(R.id.bookingDateTxt);
        }
    }
}
