package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class BusinessModel implements Parcelable {
    public String status;
    public String message;
    public List<Data> data;

    protected BusinessModel(Parcel in) {
        status = in.readString();
        message = in.readString();
    }

    public static final Creator<BusinessModel> CREATOR = new Creator<BusinessModel>() {
        @Override
        public BusinessModel createFromParcel(Parcel in) {
            return new BusinessModel(in);
        }

        @Override
        public BusinessModel[] newArray(int size) {
            return new BusinessModel[size];
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
        public String id;
        public String shop_name;
        public String address1;
        public String address2;
        public String city;
        public String state;
        public String pincode;
        public String categoryname;
        public String shopstatus;
        public String adminverifystatus;
        public String total_rate;
        public String avg_rating;
        public String image_url;

        protected Data(Parcel in) {
            id = in.readString();
            shop_name = in.readString();
            address1 = in.readString();
            address2 = in.readString();
            city = in.readString();
            state = in.readString();
            pincode = in.readString();
            categoryname = in.readString();
            shopstatus = in.readString();
            adminverifystatus = in.readString();
            total_rate = in.readString();
            avg_rating = in.readString();
            image_url = in.readString();
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
            dest.writeString(shop_name);
            dest.writeString(address1);
            dest.writeString(address2);
            dest.writeString(city);
            dest.writeString(state);
            dest.writeString(pincode);
            dest.writeString(categoryname);
            dest.writeString(shopstatus);
            dest.writeString(adminverifystatus);
            dest.writeString(total_rate);
            dest.writeString(avg_rating);
            dest.writeString(image_url);
        }
    }
}
