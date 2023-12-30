package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class OfferModel implements Parcelable {
    public String status;
    public String message;
    public List<Data> data;

    protected OfferModel(Parcel in) {
        status = in.readString();
        message = in.readString();
    }

    public static final Creator<OfferModel> CREATOR = new Creator<OfferModel>() {
        @Override
        public OfferModel createFromParcel(Parcel in) {
            return new OfferModel(in);
        }

        @Override
        public OfferModel[] newArray(int size) {
            return new OfferModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
    }

    public static class Data implements Parcelable {
        public String offer_id;
        public String offer_name;
        public String offer_desc;
        public String image_url;
        public int offer_type;
        public String offer_type_name;
        public String amount;
        public String original_amount;
        public String offer_amount;
        public String offer_percentage;
        public String flat_percentage;
        public String shopstatus;
        public String adminverifystatus;

        protected Data(Parcel in) {
            offer_id = in.readString();
            offer_name = in.readString();
            offer_desc = in.readString();
            image_url = in.readString();
            offer_type = in.readInt();
            offer_type_name = in.readString();
            amount = in.readString();
            original_amount = in.readString();
            offer_amount = in.readString();
            offer_percentage = in.readString();
            flat_percentage = in.readString();
            shopstatus = in.readString();
            adminverifystatus = in.readString();
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeString(offer_id);
            dest.writeString(offer_name);
            dest.writeString(offer_desc);
            dest.writeString(image_url);
            dest.writeInt(offer_type);
            dest.writeString(offer_type_name);
            dest.writeString(amount);
            dest.writeString(original_amount);
            dest.writeString(offer_amount);
            dest.writeString(offer_percentage);
            dest.writeString(flat_percentage);
            dest.writeString(shopstatus);
            dest.writeString(adminverifystatus);
        }
    }
}
