package com.hub.offershub.listener;

import com.hub.offershub.model.OfferModel;

public interface OfferListener {
    void onOfferSelect(OfferModel.Data model, String priority);
    void onOfferEdit(Object obj);
    void onOfferRemove(Object obj, int position);
    void onOfferInSight(Object obj);
}
