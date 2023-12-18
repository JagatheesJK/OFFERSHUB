package com.hub.offershub.model;

import java.util.List;

public class Amenity {
    private String status;
    private String message;
    private List<AmenityItem> data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<AmenityItem> getData() {
        return data;
    }

    public static class AmenityItem {
        private int id;
        private String amenities;

        public int getId() {
            return id;
        }

        public String getAmenities() {
            return amenities;
        }
    }
}