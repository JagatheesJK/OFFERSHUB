package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BookModel {
    public String status;
    public String message;
    public List<Data> data;
    public int count;

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
