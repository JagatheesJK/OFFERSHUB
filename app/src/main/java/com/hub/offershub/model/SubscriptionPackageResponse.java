package com.hub.offershub.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SubscriptionPackageResponse {
    public String status;
    public String message;
    public List<SubscriptionPackage> data;

    // Getters and setters


    public class SubscriptionPackage {
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

        // Getters and setters
    }
}
