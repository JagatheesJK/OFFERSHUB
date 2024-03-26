package com.hub.offershub.model;

import java.util.List;

public class OfferDashboardModel {
    public String status;
    public String message;
    public Data data;
    public List<BarChart> visitchart;
    public List<AgeChart> agechart;
    public List<PieChart> genderpiechart;
    public List<OrderDetails> orderdetails;

    public class Data {
        public int offervisitcount;
        public int totalfavorite;
        public int totalorders;
        public int repeatedusers;
        public int totalusers;
    }

    public static class BarChart {
        public String day;
        public int count;

        public BarChart(String s, int i) {
            this.count = i;
            this.day = s;
        }
    }

    public class AgeChart {
        public String age;
        public int value;
    }

    public class PieChart {
        public String gender;
        public int value;
    }

    public class OrderDetails {
        public String day;
        public int count;
    }
}
