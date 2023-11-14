package com.hub.offershub.model;

public class OfferModel {
    public String offerName;
    public String offerDesc;
    public String offerType;
    public String offerPrice;
    public String offerImage;
    public boolean offerToggle;

    public OfferModel(String offerName, String offerDesc, String offerType, String offerPrice, String offerImage, boolean offerToggle) {
        this.offerName = offerName;
        this.offerDesc = offerDesc;
        this.offerType = offerType;
        this.offerPrice = offerPrice;
        this.offerImage = offerImage;
        this.offerToggle = offerToggle;
    }
}
