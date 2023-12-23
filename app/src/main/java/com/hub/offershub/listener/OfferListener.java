package com.hub.offershub.listener;

public interface OfferListener {
    void onOfferSelect();
    void onOfferEdit(Object obj);
    void onOfferRemove(Object obj, int position);
    void onOfferInSight(Object obj);
}
