package com.hub.offershub.model;

import java.util.List;

public class ShopDashboardModel {
    public String status;
    public String message;
    public Data data;
    public List<BarChart> visitchart;
    public List<AgeChart> agechart;
    public List<PieChart> genderpiechart;
    public Rating ratingdata;

    public class Data {
        public int shopvisitcount;
        public int offervisitcount;
        public int contactvisitcount;
        public int mapvisitcount;
        public int totalfavorite;
        public int totalorders;
        public int repeatedusers;
        public int totalusers;
        public String avgrating;
        public int usersrated;
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

    public class Rating {
        public String rating5;
        public String rating4;
        public String rating3;
        public String rating2;
        public String rating1;
    }
}
