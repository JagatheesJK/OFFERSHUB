package com.hub.offershub.model;

import java.util.List;

public class ShopDashboardModel {
    public String status;
    public String message;
    public Data data;
    public List<BarChart> visitchart;
    public List<AgeChart> agechart;
    public List<PieChart> genderpiechart;

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

    public class BarChart {
        public String day;
        public int count;
    }

    public class AgeChart {
        public String age;
        public int value;
    }

    public class PieChart {
        public String gender;
        public int value;
    }
}
