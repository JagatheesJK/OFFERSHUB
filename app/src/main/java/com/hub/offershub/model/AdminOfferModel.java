package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class AdminOfferModel implements Parcelable {
    public String status;
    public String message;
    public int count;
    public List<Data> data;

    protected AdminOfferModel(Parcel in) {
        status = in.readString();
        message = in.readString();
        count = in.readInt();
    }

    public static final Creator<AdminOfferModel> CREATOR = new Creator<AdminOfferModel>() {
        @Override
        public AdminOfferModel createFromParcel(Parcel in) {
            return new AdminOfferModel(in);
        }

        @Override
        public AdminOfferModel[] newArray(int size) {
            return new AdminOfferModel[size];
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
        dest.writeInt(count);
    }

    public static class Data implements Parcelable {
        public String id;
        public String offer_name;
        public String offer_desc;
        public String shop_id;
        public String image_url;
        public int offer_type;
        public int is_show_mainpage;
        public String offer_type_name;
        public String amount;
        public String original_amount;
        public String offer_amount;
        public String offer_percentage;
        public String flat_percentage;
        public String valid_till;
        public String created_on;
        public String updated_on;
        public int is_active;
        public int is_admin_verified;

        protected Data(Parcel in) {
            id = in.readString();
            offer_name = in.readString();
            offer_desc = in.readString();
            shop_id = in.readString();
            image_url = in.readString();
            offer_type = in.readInt();
            is_show_mainpage = in.readInt();
            offer_type_name = in.readString();
            amount = in.readString();
            original_amount = in.readString();
            offer_amount = in.readString();
            offer_percentage = in.readString();
            flat_percentage = in.readString();
            valid_till = in.readString();
            created_on = in.readString();
            updated_on = in.readString();
            is_active = in.readInt();
            is_admin_verified = in.readInt();
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
            dest.writeString(id);
            dest.writeString(offer_name);
            dest.writeString(offer_desc);
            dest.writeString(shop_id);
            dest.writeString(image_url);
            dest.writeInt(offer_type);
            dest.writeInt(is_show_mainpage);
            dest.writeString(offer_type_name);
            dest.writeString(amount);
            dest.writeString(original_amount);
            dest.writeString(offer_amount);
            dest.writeString(offer_percentage);
            dest.writeString(flat_percentage);
            dest.writeString(valid_till);
            dest.writeString(created_on);
            dest.writeString(updated_on);
            dest.writeInt(is_active);
            dest.writeInt(is_admin_verified);
        }
    }
}
