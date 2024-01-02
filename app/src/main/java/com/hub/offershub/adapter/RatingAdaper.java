package com.hub.offershub.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.model.RatingModel;
import com.hub.offershub.utils.Utils;

import java.util.List;

public class RatingAdaper extends RecyclerView.Adapter<RatingAdaper.BookViewHolder> {

    private List<RatingModel.Data> list;
    private Context ctx;
    private CommentListener listener;

    public RatingAdaper(Context context, List<RatingModel.Data> list, CommentListener listener) {
        ctx = context;
        this.list = list;
        this.listener = listener;
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
            if (model.created_on != null && model.created_on.length() > 0)
                holder.ratingTimeTxt.setText(""+ Utils.convertDateFormat(model.created_on));
            holder.ratingUserCommentTxt.setText(""+model.user_comments);
            holder.ratingShopCommentTxt.setText(""+model.shop_comments);
            if (model.user_comments != null && model.user_comments.length() > 0)
                holder.ratingUserCommentTxt.setVisibility(View.VISIBLE);
            else
                holder.ratingUserCommentTxt.setVisibility(View.GONE);

            if (model.shop_comments != null && model.shop_comments.length() > 0)
                holder.ratingShopCommentTxt.setVisibility(View.VISIBLE);
            else
                holder.ratingShopCommentTxt.setVisibility(View.GONE);

            holder.replyLinear.setOnClickListener(v -> {
                listener.onReplay(model);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        AppCompatRatingBar ratingBar;
        AppCompatTextView nameTxt, ratingTimeTxt;
        AppCompatTextView ratingUserCommentTxt, ratingShopCommentTxt;
        LinearLayoutCompat replyLinear;
        public BookViewHolder(View v) {
            super(v);
            ratingBar = v.findViewById(R.id.ratingBar);
            nameTxt = v.findViewById(R.id.nameTxt);
            ratingTimeTxt = v.findViewById(R.id.ratingTimeTxt);
            ratingUserCommentTxt = v.findViewById(R.id.ratingUserCommentTxt);
            ratingShopCommentTxt = v.findViewById(R.id.ratingShopCommentTxt);
            replyLinear = v.findViewById(R.id.replyLinear);
        }
    }

    public void addData(RatingModel.Data model) {
        list.add(model);
        notifyItemInserted(list.size() - 1);
    }

    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void addAll(List<RatingModel.Data> ratingList) {
        for (RatingModel.Data result : ratingList) {
            addData(result);
        }
    }

    public void changeData(int position, RatingModel.Data model) {
        Log.e("Check_Adapter", "changeData position : "+position);
        list.set(position, model);
        notifyItemChanged(position);
    }

    public interface CommentListener {
        void onReplay(RatingModel.Data model);
    }
}
