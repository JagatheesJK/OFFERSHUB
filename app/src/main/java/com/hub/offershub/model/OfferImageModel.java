package com.hub.offershub.model;

import java.util.List;

public class OfferImageModel {
    public String status;
    public String message;
    public List<Data> data;

    public static class Data {
        public int id;
        public String image_stored_path;
        public String adminverifystatus;
        public String created_on;
        public String status;
    }
}
