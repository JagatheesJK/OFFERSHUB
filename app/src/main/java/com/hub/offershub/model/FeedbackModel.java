package com.hub.offershub.model;

import java.util.List;

public class FeedbackModel {
    public String status;
    public String message;
    public List<Data> data;

    public static class Data {
        public int id;
        public String name;
        public String feedback;
        public String adminreply;
        public String created_on;
    }
}
