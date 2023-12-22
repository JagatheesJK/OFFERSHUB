package com.hub.offershub.listener;

public interface OfferListener {
    void onOfferSelect();
    void onOfferEdit(Object obj);
    void onOfferRemove(Object obj);
    void onOfferInSight(Object obj);
}
