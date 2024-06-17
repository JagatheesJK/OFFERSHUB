package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class AdminShopModel implements Parcelable {
    public String status;
    public String message;
    public int count;
    public List<Data> data;

    protected AdminShopModel(Parcel in) {
        status = in.readString();
        message = in.readString();
        count = in.readInt();
    }

    public static final Creator<AdminShopModel> CREATOR = new Creator<AdminShopModel>() {
        @Override
        public AdminShopModel createFromParcel(Parcel in) {
            return new AdminShopModel(in);
        }

        @Override
        public AdminShopModel[] newArray(int size) {
            return new AdminShopModel[size];
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
        public String shop_name;
        public String mobile;
        public String upi_details;
        public String latitude;
        public String longitude;
        public String address1;
        public String address2;
        public String city;
        public String state;
        public String pincode;
        public String shop_owner_id;
        public String category_id;
        public int subscription_id;
        public String subscription_status;
        public String subscription_end_date;
        public String created_on;
        public String updated_on;
        public int is_active;
        public int is_admin_verified;

        protected Data(Parcel in) {
            id = in.readString();
            shop_name = in.readString();
            mobile = in.readString();
            upi_details = in.readString();
            latitude = in.readString();
            longitude = in.readString();
            address1 = in.readString();
            address2 = in.readString();
            city = in.readString();
            state = in.readString();
            pincode = in.readString();
            shop_owner_id = in.readString();
            category_id = in.readString();
            subscription_id = in.readInt();
            subscription_status = in.readString();
            subscription_end_date = in.readString();
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
            dest.writeString(shop_name);
            dest.writeString(mobile);
            dest.writeString(upi_details);
            dest.writeString(latitude);
            dest.writeString(longitude);
            dest.writeString(address1);
            dest.writeString(address2);
            dest.writeString(city);
            dest.writeString(state);
            dest.writeString(pincode);
            dest.writeString(shop_owner_id);
            dest.writeString(category_id);
            dest.writeInt(subscription_id);
            dest.writeString(subscription_status);
            dest.writeString(subscription_end_date);
            dest.writeString(created_on);
            dest.writeString(updated_on);
            dest.writeInt(is_active);
            dest.writeInt(is_admin_verified);
        }
    }
}
