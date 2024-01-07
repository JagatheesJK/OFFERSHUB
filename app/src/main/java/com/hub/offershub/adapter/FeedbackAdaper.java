package com.hub.offershub.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.model.FeedbackModel;
import com.hub.offershub.utils.Utils;

import java.util.List;

public class FeedbackAdaper extends RecyclerView.Adapter<FeedbackAdaper.BookViewHolder> {

    private List<FeedbackModel.Data> list;
    private Context ctx;

    public FeedbackAdaper(Context context, List<FeedbackModel.Data> list) {
        ctx = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback_layout, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        FeedbackModel.Data model = list.get(position);
        if (model != null) {
            holder.feedbackName.setText("You");
            if (model.created_on != null && model.created_on.length() > 0)
                holder.feedbackTime.setText(""+ Utils.convertDateFormat(model.created_on));
            holder.feedbackMsg.setText(""+model.feedback);
            holder.feedbackAdminCommentTxt.setText(""+model.adminreply);
            if (model.adminreply != null && !"null".equals(model.adminreply) && model.adminreply.length() > 0) {
                holder.feedbackAdminCommentLinear.setVisibility(View.VISIBLE);
            } else {
                holder.feedbackAdminCommentLinear.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView feedbackName, feedbackTime, feedbackMsg;
        LinearLayoutCompat feedbackAdminCommentLinear;
        AppCompatTextView feedbackAdminCommentTxt;
        public BookViewHolder(View v) {
            super(v);
            feedbackName = v.findViewById(R.id.feedbackName);
            feedbackTime = v.findViewById(R.id.feedbackTime);
            feedbackMsg = v.findViewById(R.id.feedbackMsg);
            feedbackAdminCommentLinear = v.findViewById(R.id.feedbackAdminCommentLinear);
            feedbackAdminCommentTxt = v.findViewById(R.id.feedbackAdminCommentTxt);
        }
    }

    public void addData(FeedbackModel.Data model) {
        list.add(model);
        notifyItemInserted(list.size() - 1);
    }

    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void addAll(List<FeedbackModel.Data> ratingList) {
        for (FeedbackModel.Data result : ratingList) {
            addData(result);
        }
    }

    public void changeData(int position, FeedbackModel.Data model) {
        Log.e("Check_Adapter", "changeData position : "+position);
        list.set(position, model);
        notifyItemChanged(position);
    }

}
