package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class BookModel implements Parcelable {
    public String status;
    public String message;
    public List<Data> data;
    public int count;
    public int mobileviewedcount;

    protected BookModel(Parcel in) {
        status = in.readString();
        message = in.readString();
        data = in.createTypedArrayList(Data.CREATOR);
        count = in.readInt();
        mobileviewedcount = in.readInt();
    }

    public static final Creator<BookModel> CREATOR = new Creator<BookModel>() {
        @Override
        public BookModel createFromParcel(Parcel in) {
            return new BookModel(in);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
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
        dest.writeTypedList(data);
        dest.writeInt(count);
        dest.writeInt(mobileviewedcount);
    }

    public static class Data implements Parcelable {
        public int id;
        public int is_shop_read;
        public String user_ordered_date;
        public String shop_visited_date;
        public int is_active;
        public int is_mobilenumber_viewed;
        public int offer_id;
        public String offer_name;
        public String offer_desc;
        public int offer_type;
        public String amount;
        public String original_amount;
        public String offer_amount;
        public String offer_percentage;
        public String flat_percentage;
        public String image_url;
        public String name;
        public long fullmobile;
        public String mobile;
        public String userorder_status;
        public String shoporder_status;
        public int shop_id;
        public String shop_name;
        public String subscription_status;
        public int mobilecount;

        protected Data(Parcel in) {
            id = in.readInt();
            shop_id = in.readInt();
            is_shop_read = in.readInt();
            user_ordered_date = in.readString();
            shop_visited_date = in.readString();
            is_active = in.readInt();
            is_mobilenumber_viewed = in.readInt();
            offer_id = in.readInt();
            offer_name = in.readString();
            offer_desc = in.readString();
            offer_type = in.readInt();
            amount = in.readString();
            original_amount = in.readString();
            offer_amount = in.readString();
            offer_percentage = in.readString();
            flat_percentage = in.readString();
            image_url = in.readString();
            name = in.readString();
            fullmobile = in.readLong();
            mobile = in.readString();
            userorder_status = in.readString();
            shoporder_status = in.readString();
            shop_name = in.readString();
            subscription_status = in.readString();
            mobilecount = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(shop_id);
            dest.writeInt(is_shop_read);
            dest.writeString(user_ordered_date);
            dest.writeString(shop_visited_date);
            dest.writeInt(is_active);
            dest.writeInt(is_mobilenumber_viewed);
            dest.writeInt(offer_id);
            dest.writeString(offer_name);
            dest.writeString(offer_desc);
            dest.writeInt(offer_type);
            dest.writeString(amount);
            dest.writeString(original_amount);
            dest.writeString(offer_amount);
            dest.writeString(offer_percentage);
            dest.writeString(flat_percentage);
            dest.writeString(image_url);
            dest.writeString(name);
            dest.writeLong(fullmobile);
            dest.writeString(mobile);
            dest.writeString(userorder_status);
            dest.writeString(shoporder_status);
            dest.writeString(shop_name);
            dest.writeString(subscription_status);
            dest.writeInt(mobilecount);
        }

        @Override
        public int describeContents() {
            return 0;
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
    }
}
