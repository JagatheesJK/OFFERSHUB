package com.hub.offershub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hub.offershub.R;
import com.hub.offershub.listener.OfferListener;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.CommonMethods;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<OfferModel.Data> list;
    private Context ctx;
    private CommonMethods commonMethods;
    private OfferListener listener;

    public OfferAdapter(Context context, List<OfferModel.Data> list, OfferListener listener) {
        this.list = list;
        ctx = context;
        this.listener = listener;
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
            holder.offerNameTxt.setText(""+model.offer_name);
            holder.offerDescTxt.setText(""+model.offer_desc);
            holder.offerType.setText("Type : "+model.offer_type_name);
            holder.offerPrice.setText(""+model.offer_amount);
//            holder.offerSwitch.setChecked(model.offerToggle);
            commonMethods.imageLoaderView(ctx, holder.offerImg, model.image_url);

            holder.itemView.setOnClickListener(v -> {
                listener.onOfferSelect();
            });

            holder.deleteLinear.setOnClickListener(v -> {
                list.remove(model);
                notifyDataSetChanged();
                listener.onOfferRemove();
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView offerNameTxt, offerDescTxt;
        AppCompatTextView offerType, offerPrice;
        AppCompatImageView offerImg;
        SwitchCompat offerSwitch;
        LinearLayoutCompat editLinear, deleteLinear;
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
        }
    }
}