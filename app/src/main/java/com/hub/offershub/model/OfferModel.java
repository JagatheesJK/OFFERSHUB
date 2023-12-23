package com.hub.offershub.model;

import java.util.List;

public class OfferModel {
    public String status;
    public String message;
    public List<Data> data;

    public static class Data {
        public String offer_id;
        public String offer_name;
        public String offer_desc;
        public String image_url;
        public int offer_type;
        public String offer_type_name;
        public String amount;
        public String original_amount;
        public String offer_amount;
        public String offer_percentage;
        public String flat_percentage;
        public String shopstatus;
        public String adminverifystatus;
    }
}
