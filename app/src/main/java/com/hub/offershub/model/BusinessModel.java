package com.hub.offershub.model;

import java.util.List;

public class BusinessModel {
    public String status;
    public String message;
    public List<Data> data;

    public static class Data {
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
    }
}
