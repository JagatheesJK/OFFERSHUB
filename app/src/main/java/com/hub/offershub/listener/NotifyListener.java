package com.hub.offershub.listener;

import com.hub.offershub.model.BookModel;

public interface NotifyListener {
    void onNotifySelect(BookModel.Data model);
}