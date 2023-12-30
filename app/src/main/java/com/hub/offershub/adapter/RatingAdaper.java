package com.hub.offershub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.model.RatingModel;

import java.util.List;

public class RatingAdaper extends RecyclerView.Adapter<RatingAdaper.BookViewHolder> {

    private List<RatingModel.Data> list;
    private Context ctx;

    public RatingAdaper(Context context, List<RatingModel.Data> list) {
        this.list = list;
        ctx = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating_layout, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        RatingModel.Data model = list.get(position);
        if (model != null) {
            holder.ratingBar.setRating(model.rating);
            holder.nameTxt.setText(""+model.name);
            holder.ratingTimeTxt.setText(""+model.created_on);
            holder.ratingCommentTxt.setText(""+model.user_comments);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        AppCompatRatingBar ratingBar;
        AppCompatTextView nameTxt, ratingTimeTxt, ratingCommentTxt;
        public BookViewHolder(View v) {
            super(v);
            ratingBar = v.findViewById(R.id.ratingBar);
            nameTxt = v.findViewById(R.id.nameTxt);
            ratingTimeTxt = v.findViewById(R.id.ratingTimeTxt);
            ratingCommentTxt = v.findViewById(R.id.ratingCommentTxt);
        }
    }
}
