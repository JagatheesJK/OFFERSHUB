package com.hub.offershub.model;

import java.util.List;

public class CategoryResponse {

    private String status;
    private String message;
    private String title;
    private int count;
    private List<Category> data;

    // Constructor
    public CategoryResponse(String status, String message, String title, int count, List<Category> data) {
        this.status = status;
        this.message = message;
        this.title = title;
        this.count = count;
        this.data = data;
    }

    // Getters
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }

    public List<Category> getData() {
        return data;
    }

    public class Category {
        private int id;
        private String categoryname;
        private int offerCount;
        private String imageUrl;
        private int isActive;

        // Constructor
        public Category(int id, String categoryName, int offerCount, String imageUrl, int isActive) {
            this.id = id;
            this.categoryname = categoryName;
            this.offerCount = offerCount;
            this.imageUrl = imageUrl;
            this.isActive = isActive;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getCategoryName() {
            return categoryname;
        }

        public int getOfferCount() {
            return offerCount;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getIsActive() {
            return isActive;
        }
    }
}
