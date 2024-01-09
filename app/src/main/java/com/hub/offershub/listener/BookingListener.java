package com.hub.offershub.listener;

import com.hub.offershub.model.BookModel;

public interface BookingListener {
    void onBookingSelect(BookModel.Data model);
}