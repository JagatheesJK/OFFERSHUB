package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class AdminOfferImageModel implements Parcelable {
    public String status;
    public String message;
    public int count;
    public List<Data> data;

    protected AdminOfferImageModel(Parcel in) {
        status = in.readString();
        message = in.readString();
        count = in.readInt();
    }

    public static final Creator<AdminOfferImageModel> CREATOR = new Creator<AdminOfferImageModel>() {
        @Override
        public AdminOfferImageModel createFromParcel(Parcel in) {
            return new AdminOfferImageModel(in);
        }

        @Override
        public AdminOfferImageModel[] newArray(int size) {
            return new AdminOfferImageModel[size];
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
        public int id;
        public int offer_id;
        public int shop_id;
        public String image_stored_path;
        public String created_on;
        public int is_active;
        public int is_admin_verified;

        protected Data(Parcel in) {
            id = in.readInt();
            offer_id = in.readInt();
            shop_id = in.readInt();
            image_stored_path = in.readString();
            created_on = in.readString();
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
            dest.writeInt(id);
            dest.writeInt(offer_id);
            dest.writeInt(shop_id);
            dest.writeString(image_stored_path);
            dest.writeString(created_on);
            dest.writeInt(is_active);
            dest.writeInt(is_admin_verified);
        }
    }
}
