package com.hub.offershub.listener;

import com.hub.offershub.model.OfferModel;
import com.hub.offershub.model.SubscriptionPackageResponse;

public interface onPlanSelectListener {
    void onPlanSelect(SubscriptionPackageResponse.SubscriptionPackage model);
}
