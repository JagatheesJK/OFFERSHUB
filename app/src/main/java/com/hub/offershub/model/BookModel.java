package com.hub.offershub.model;

public class BookModel {
    public String offerName;
    public String userName, userMobile;
    public String bookingDate;

    public BookModel(String offerName, String userName, String userMobile, String bookingDate) {
        this.offerName = offerName;
        this.userName = userName;
        this.userMobile = userMobile;
        this.bookingDate = bookingDate;
    }
}
