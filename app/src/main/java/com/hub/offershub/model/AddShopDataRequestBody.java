package com.hub.offershub.model;

import java.util.List;

public class AddShopDataRequestBody {
    public int shopownerid;
    public String shopname;
    public Long mobile;
    public String upi;
    public List<Integer> shopamenities; // Change to List<String> for array
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String pincode;
    public int categoryid;
    public double latitude;
    public double longitude;
}
