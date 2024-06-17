package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class AdminShopImageModel implements Parcelable {
    public String status;
    public String message;
    public int count;
    public List<Data> data;

    protected AdminShopImageModel(Parcel in) {
        status = in.readString();
        message = in.readString();
        count = in.readInt();
    }

    public static final Creator<AdminShopImageModel> CREATOR = new Creator<AdminShopImageModel>() {
        @Override
        public AdminShopImageModel createFromParcel(Parcel in) {
            return new AdminShopImageModel(in);
        }

        @Override
        public AdminShopImageModel[] newArray(int size) {
            return new AdminShopImageModel[size];
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
        public int shop_id;
        public String image_stored_path;
        public String created_on;
        public int is_active;
        public int is_admin_verified;

        protected Data(Parcel in) {
            id = in.readInt();
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
            dest.writeInt(shop_id);
            dest.writeString(image_stored_path);
            dest.writeString(created_on);
            dest.writeInt(is_active);
            dest.writeInt(is_admin_verified);
        }
    }
}
