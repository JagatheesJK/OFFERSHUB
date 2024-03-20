package com.hub.offershub.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SubscriptionPackageResponse implements Parcelable {
    public String status;
    public String message;
    public List<SubscriptionPackage> data;

    protected SubscriptionPackageResponse(Parcel in) {
        status = in.readString();
        message = in.readString();
    }

    public static final Creator<SubscriptionPackageResponse> CREATOR = new Creator<SubscriptionPackageResponse>() {
        @Override
        public SubscriptionPackageResponse createFromParcel(Parcel in) {
            return new SubscriptionPackageResponse(in);
        }

        @Override
        public SubscriptionPackageResponse[] newArray(int size) {
            return new SubscriptionPackageResponse[size];
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

    // Getters and setters


    public static class SubscriptionPackage implements Parcelable {
        public int id;
        @SerializedName("package_name")
        public String packageName;
        @SerializedName("package_code")
        public String packageCode;
        public int days;
        public int price;
        @SerializedName("is_active")
        public int isActive;
        public List<String> desc;

        protected SubscriptionPackage(Parcel in) {
            id = in.readInt();
            packageName = in.readString();
            packageCode = in.readString();
            days = in.readInt();
            price = in.readInt();
            isActive = in.readInt();
            desc = in.createStringArrayList();
        }

        public static final Creator<SubscriptionPackage> CREATOR = new Creator<SubscriptionPackage>() {
            @Override
            public SubscriptionPackage createFromParcel(Parcel in) {
                return new SubscriptionPackage(in);
            }

            @Override
            public SubscriptionPackage[] newArray(int size) {
                return new SubscriptionPackage[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(packageName);
            dest.writeString(packageCode);
            dest.writeInt(days);
            dest.writeInt(price);
            dest.writeInt(isActive);
            dest.writeStringList(desc);
        }
    }
}
