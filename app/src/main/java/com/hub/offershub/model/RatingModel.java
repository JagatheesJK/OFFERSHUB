package com.hub.offershub.model;

import java.util.List;

public class RatingModel {
    public String status;
    public String message;
    public List<Data> data;

    public static class Data {
        public int id;
        public int user_id;
        public String name;
        public int rating;
        public String user_comments;
        public String shop_comments;
        public String created_on;
    }
}
