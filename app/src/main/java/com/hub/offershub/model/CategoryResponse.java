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
        private String description;
        private int type;
        private String url;
        private String created_on;
        private int isActive;

        // Constructor
        public Category(int id, String categoryname, String description, int type, String url, String created_on, int isActive) {
            this.id = id;
            this.categoryname = categoryname;
            this.description = description;
            this.type = type;
            this.url = url;
            this.created_on = created_on;
            this.isActive = isActive;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getCategoryName() {
            return categoryname;
        }

        public String getDescription() {
            return description;
        }

        public int getType() {
            return type;
        }

        public String getImageUrl() {
            return url;
        }

        public String getCreated_on() {
            return created_on;
        }

        public int getIsActive() {
            return isActive;
        }
    }
}
