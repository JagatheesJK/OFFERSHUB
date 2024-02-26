package com.hub.offershub.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

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
import com.hub.offershub.listener.onPlanSelectListener;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.model.SubscriptionPackageResponse;
import com.hub.offershub.utils.CommonMethods;

import java.util.List;

public class SubScriptionAdapter extends RecyclerView.Adapter<SubScriptionAdapter.ViewHolder> {

    private List<SubscriptionPackageResponse.SubscriptionPackage> list;
    private Context ctx;
    private CommonMethods commonMethods;
    private int selectedPosition = 1;
    onPlanSelectListener listener;
    String priority;
    private boolean isFirst = true;

    public SubScriptionAdapter(Context context, onPlanSelectListener listener, List<SubscriptionPackageResponse.SubscriptionPackage> list) {
        this.list = list;
        this.listener = listener;
        ctx = context;
        commonMethods = new CommonMethods();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubscriptionPackageResponse.SubscriptionPackage model = list.get(position);
        if (model != null) {
            if(model.isActive ==1){
                holder.plantitle.setText(model.packageName);
                holder.planCode.setText(model.packageCode);
                holder.planDesc.setText(model.desc.get(0));
                holder.planAmt.setText("₹ "+model.price);
                holder.planDays.setText(+model.days+" days");
                holder.planRadio.setChecked(selectedPosition == position);
            }

        }
        holder.itemView.setOnClickListener(v -> {
            // Update selectedPosition and notify data changed
            if (selectedPosition != position) {
                selectedPosition = position;
                notifyDataSetChanged(); // Notify adapter to update checked state
                if(listener != null){
                    listener.onPlanSelect(model);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView plantitle, planCode;
        AppCompatTextView planDesc, planAmt,planDays;
        RadioButton planRadio;

        public ViewHolder(View v) {
            super(v);
            plantitle = v.findViewById(R.id.plantitle);
            planCode = v.findViewById(R.id.planCode);
            planDesc = v.findViewById(R.id.planDesc);
            planAmt = v.findViewById(R.id.planAmt);
            planDays = v.findViewById(R.id.planDays);
            planRadio = v.findViewById(R.id.planRadio);

        }
    }
}