package com.hub.offershub.model;

import java.util.List;

public class BookModel {
    public String status;
    public String message;
    public List<Data> data;
    public int count;

    public static class Data {
        public int id;
        public int is_shop_read;
        public String user_ordered_date;
        public String shop_visited_date;
        public int is_active;
        public int is_mobilenumber_viewed;
        public int offer_id;
        public String offer_name;
        public String offer_desc;
        public String name;
        public double fullmobile;
        public String mobile;
        public String userstatus;
        public String shopstatus;
    }
}
